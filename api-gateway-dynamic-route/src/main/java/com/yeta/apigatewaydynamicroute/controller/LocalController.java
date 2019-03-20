package com.yeta.apigatewaydynamicroute.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API网关本地跳转接口
 * @author YETA
 * @date 2019/03/15/20:11
 */
@RestController
@RequestMapping(value = "/local")
public class LocalController {

    @GetMapping(value = "/hello")
    public Object local() {
        return "hello from local forward";
    }
}
