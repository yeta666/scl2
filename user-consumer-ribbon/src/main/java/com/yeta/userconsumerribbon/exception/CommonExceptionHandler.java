package com.yeta.userconsumerribbon.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 * @author YETA
 * @date 2018/11/27/13:09
 */
@RestControllerAdvice
public class CommonExceptionHandler  {

    @ExceptionHandler
    public String defaultExceptionHandler(Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }
}
