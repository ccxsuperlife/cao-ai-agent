package com.cao.caoaiagent.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cao.caoaiagent.mapper.ChatMessageMapper;
import com.cao.caoaiagent.model.domain.entity.ChatMessage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小曹同学
 * @date 2025/5/30
 */
@Component
public class ChatMessageDAO extends ServiceImpl<ChatMessageMapper, ChatMessage> {

    public List<ChatMessage> getMessages(String conversationId) {
        return this.lambdaQuery()
                .eq(ChatMessage::getConversationId, conversationId)
                .list();
    }

    public void deleteMessages(String conversationId) {
        this.lambdaUpdate()
                .eq(ChatMessage::getConversationId, conversationId)
                .remove();
    }
}
