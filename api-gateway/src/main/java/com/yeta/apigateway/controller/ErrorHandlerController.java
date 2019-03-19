package com.yeta.apigateway.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理接口
 * @author YETA
 * @date 2019/03/19/15:53
 */
@RestController
public class ErrorHandlerController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping(value = "/error")
    public Object error(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put("status_code", request.getAttribute("javax.servlet.error.status_code"));
        StringBuffer sb = new StringBuffer(request.getAttribute("javax.servlet.error.message").toString());
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        while (exception.getCause() != null) {
            sb.append("  ").append(exception.getCause().getMessage());
            exception = (Exception) exception.getCause();
        }
        result.put("message", sb);

        return result;
    }
}
