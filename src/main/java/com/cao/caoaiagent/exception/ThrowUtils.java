package com.cao.caoaiagent.exception;


/**
 * 异常处理工具类
 */
public class ThrowUtils {

    /**
     * 条件成立则抛出异常
     *
     * @param condition        判断条件
     * @param runtimeException 异常
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛出异常
     *
     * @param condition 判断条件
     * @param errorCode 错误码
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        if (condition) {
            throw new BusinessException( errorCode );
        }
    }


    /**
     * 条件成立则抛出异常
     *
     * @param condition 判断条件
     * @param errorCode 错误码
     * @param message   错误信息
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        if (condition) {
            throw new BusinessException( errorCode, message );
        }
    }


    /**
     * 条件成立则抛出异常
     *
     * @param condition 判断条件
     * @param code 错误码
     * @param message   错误信息
     */
    public static void throwIf(boolean condition, int code, String message) {
        if (condition) {
            throw new BusinessException( code, message );
        }
    }
}
