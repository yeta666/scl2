package com.yeta.configclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YETA
 * @date 2019/03/20/12:58
 */
@RefreshScope
@RestController
public class TestController {

    @Value("${from}")
    private String from;

    @Autowired
    private Environment environment;

    @GetMapping(value = "/from")
    public Object from() {
        return from;
    }

    @GetMapping(value = "/from1")
    public Object from1() {
        return environment.getProperty("from", "undefined");
    }
}
