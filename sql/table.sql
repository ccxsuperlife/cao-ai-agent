create database yu_ai_agent;
use yu_ai_agent;
DROP TABLE IF EXISTS `chat_message`;
CREATE TABLE IF NOT EXISTS `chat_message`
(
    `id`              bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `conversation_id` varchar(64)     NOT NULL COMMENT '会话ID',
    `message_type`    varchar(20)     NOT NULL COMMENT '消息类型',
    `content`         text            NOT NULL COMMENT '消息内容',
    `create_time`     datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`       tinyint(1)      NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    INDEX `idx_conversation_id` (`conversation_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='聊天消息表';
