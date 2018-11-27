package com.yeta.userconsumerribbon.exception;

/**
 * 自定义异常
 * @author YETA
 * @date 2018/11/27/13:04
 */
public class CommonException extends RuntimeException {

    public CommonException() {
        super();
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }
}
