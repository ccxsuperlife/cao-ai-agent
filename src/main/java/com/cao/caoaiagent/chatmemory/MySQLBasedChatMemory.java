package com.cao.caoaiagent.chatmemory;

import cn.hutool.core.collection.CollectionUtil;
import com.cao.caoaiagent.dao.ChatMessageDAO;
import com.cao.caoaiagent.enums.MessageTypeEnum;
import com.cao.caoaiagent.model.domain.entity.ChatMessage;
import com.google.gson.Gson;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 小曹同学
 * @date 2025/5/30
 * 基于MySQL持久化的对话记忆
 */
@Component
public class MySQLBasedChatMemory implements ChatMemory {

    private final ChatMessageDAO chatMessageDAO;

    public MySQLBasedChatMemory(ChatMessageDAO chatMessageDAO) {
        this.chatMessageDAO = chatMessageDAO;
    }

    @Override
    public void add(String conversationId, List<Message> messages) {
        Gson gson = new Gson();
        List<ChatMessage> conversationMemories = messages.stream().map(message -> {
            String type = message.getMessageType().getValue();
            String content = gson.toJson(message);
            return ChatMessage.builder()
                    .conversationId(conversationId)
                    .messageType(type)
                    .content(content)
                    .build();
        }).toList();
        chatMessageDAO.saveBatch(conversationMemories);
    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        List<ChatMessage> messages = chatMessageDAO.getMessages(conversationId);
        if (CollectionUtil.isEmpty(messages)) {
            return List.of();
        }
        return messages.stream()
                .skip(Math.max(0, messages.size() - lastN))
                .map(this::toMessage)
                .collect(Collectors.toList());
    }

    @Override
    public void clear(String conversationId) {
        chatMessageDAO.deleteMessages(conversationId);
    }

    /**
     * 将ChatMessage转换为Message
     */
    private Message toMessage(ChatMessage chatMessage) {
        Gson gson = new Gson();
        String content = chatMessage.getContent();
        return (Message) gson.fromJson(content, MessageTypeEnum.fromValue(chatMessage.getMessageType()).getClazz());
    }
}
