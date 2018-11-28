package com.yeta.userconsumerfeign.exception;

import com.yeta.userconsumerfeign.util.CommonResponse;
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
    public CommonResponse defaultExceptionHandler(Exception e) {
        e.printStackTrace();
        return new CommonResponse(CommonResponse.CODE3, e.getMessage(), CommonResponse.MESSAGE3);
    }
}
