package com.yeta.userconsumerfeign;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients     //开启Feign功能
@EnableDiscoveryClient      //服务发现
public class UserConsumerFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserConsumerFeignApplication.class, args);
    }

    /**
     * 调整Feign的日志级别
     * NONE：不记录任何信息
     * BASIC：仅记录请求方法、URL以及响应状态码和执行时间按
     * HEADERS：除了记录BASIC级别的信息以外，还会记录请求和响应的头信息
     * FULL：记录所有请求和响应的明细，包括头信息、请求体、元数据等
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
