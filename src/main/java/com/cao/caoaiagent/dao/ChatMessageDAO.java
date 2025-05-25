package com.cao.caoaiagent.dao;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cao.caoaiagent.mapper.ChatMessageMapper;
import com.cao.caoaiagent.model.domain.entity.ChatMessage;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小曹同学
 * @date 2025/5/30
 */
@Component
public class ChatMessageDAO extends ServiceImpl<ChatMessageMapper, ChatMessage> {

    @Resource
    private ChatMessageMapper chatMessageMapper;

    public List<ChatMessage> getMessages(String conversationId) {
        LambdaQueryChainWrapper<ChatMessage> lambdaQueryChainWrapper = this.lambdaQuery().eq(ChatMessage::getConversationId, conversationId);
        return chatMessageMapper.selectList(lambdaQueryChainWrapper);
    }

    public boolean deleteMessages(String conversationId) {
        int delete = chatMessageMapper.delete(this.lambdaQuery().eq(ChatMessage::getConversationId, conversationId));
        return delete > 0;
    }
}
