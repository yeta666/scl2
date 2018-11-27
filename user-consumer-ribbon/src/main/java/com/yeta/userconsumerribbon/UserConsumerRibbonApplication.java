package com.yeta.userconsumerribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
//@EnableDiscoveryClient        //服务发现
//@EnableCircuitBreaker     //开启断路器
@SpringCloudApplication     //等于上面三个注解加起来
public class UserConsumerRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserConsumerRibbonApplication.class, args);
    }

    /**
     * 注解@Bean用于注入RestTemplate来调用服务
     * 注解@LoadBalanced用于开启客户端负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
