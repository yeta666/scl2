package com.yeta.apigateway;

import com.yeta.apigateway.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy        //开启Zuul的API网关服务功能
@SpringCloudApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    /**
     * 启动过滤器
     * @return
     */
    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

    /**
     * 创建类似/v1/userservice/**的路由匹配规则
     * @return
     */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
          "(?<name>^.+)-(?<version>v.+$)",      //匹配服务名称是否复合该自定义规则的正则表达式
          "${version}/${name}"      //定义根据服务名中定义的内容转换出的路径表达式规则
        );
    }
}
