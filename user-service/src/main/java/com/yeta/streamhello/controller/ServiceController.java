package com.yeta.streamhello.controller;

import com.netflix.discovery.DiscoveryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YETA
 * @date 2019/03/06/17:10
 */
@RestController
@RequestMapping(value = "/service")
public class ServiceController {

    @Autowired
    private DiscoveryClient client;

    @GetMapping(value = "/shutdown")
    public String shutdown() {
        DiscoveryManager.getInstance().shutdownComponent();
        return "shutdown success";
    }
}
