package com.cao.caoaiagent.enums;

import lombok.Getter;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.ToolResponseMessage;
import org.springframework.ai.chat.messages.UserMessage;

/**
 * 消息类型枚举
 */
@Getter
public enum MessageTypeEnum {

    /**
     * @see UserMessage
     */
    USER("user", UserMessage.class),

    /**
     * @see AssistantMessage
     */
    ASSISTANT("assistant", AssistantMessage.class),

    /**
     * @see SystemMessage
     */
    SYSTEM("system", SystemMessage.class),

    /**
     * @see ToolResponseMessage
     */
    TOOL("tool", ToolResponseMessage.class);

    private final String value;

    private final Class<?> clazz;

    MessageTypeEnum(String value, Class<?> clazz) {
        this.value = value;
        this.clazz = clazz;
    }

    public static MessageTypeEnum fromValue(String value) {
        for (MessageTypeEnum messageType : MessageTypeEnum.values()) {
            if (messageType.getValue().equals(value)) {
                return messageType;
            }
        }
        throw new IllegalArgumentException("Invalid MessageType value: " + value);
    }

}

