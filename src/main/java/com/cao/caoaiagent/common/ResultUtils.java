package com.cao.caoaiagent.common;


import com.cao.caoaiagent.exception.ErrorCode;

/**
 * 返回结果工具类
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return 响应
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>( 0, data, "ok" );
    }

    /**
     * 失败
     * @param errorCode 错误码
     * @return 响应
     * @param <T> 数据类型
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode) {
        return new BaseResponse<>( errorCode );
    }

    /**
     *
     * @param errorCode 错误码
     * @param message 错误信息
     * @return
     * @param <T> 数据类型
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode, String message) {
        return new BaseResponse<>( errorCode.getCode(), null, message );
    }

    /**
     *
     * @param code 错误码
     * @param message 错误信息
     * @return
     * @param <T> 数据类型
     */
    public static <T> BaseResponse<T> error(int code, String message) {
        return new BaseResponse<>( code, null, message );
    }
}
