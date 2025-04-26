package com.cao.caoaiagent.exception;

import lombok.Getter;

/**
 * 通用错误码
 */
@Getter
public enum ErrorCode {
    /**
     * 枚举常量
     */
    SUCCESS( 0, "OK" ),
    PARAMS_ERROR( 40000, "请求参数错误" ),
    TOO_MANY_REQUEST(42900,"请求过于频繁"),
    NOT_LOGIN_ERROR( 40100, "未登录" ),
    NO_AUTH_ERROR( 40101, "无权限" ),
    NOT_FOUND_ERROR( 40400, "请求参数不存在" ),
    FORBIDDEN_ERROR( 40300, "禁止访问" ),
    SYSTEM_ERROR( 50000, "系统错误" ),
    OPERATION_ERROR( 50100, "操作错误" );

    /**
     * 状态码
     */
    private final int code;
    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
