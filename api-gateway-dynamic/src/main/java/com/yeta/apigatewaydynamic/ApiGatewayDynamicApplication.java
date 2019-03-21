package com.yeta.apigatewaydynamic;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.yeta.apigatewaydynamic.config.FilterConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy        ////开启Zuul的API网关服务功能
@EnableConfigurationProperties({FilterConfiguration.class})
@SpringCloudApplication
public class ApiGatewayDynamicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayDynamicApplication.class, args);
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

    /**
     * 创建动态加载过滤器实例
     * API网关应用会每隔filterConfiguration.getInterval()秒，从API网关服务所在位置的filter/pre和filter/post目录下获取Groovy定义的高过滤器，并对其进行编译和动态加载使用
     * @param filterConfiguration
     * @return
     */
    @Bean
    public FilterLoader filterLoader(FilterConfiguration filterConfiguration) {
        FilterLoader filterLoader = FilterLoader.getInstance();
        filterLoader.setCompiler(new GroovyCompiler());
        try {
            FilterFileManager.setFilenameFilter(new GroovyFileFilter());
            FilterFileManager.init(
                    filterConfiguration.getInterval(),
                    filterConfiguration.getRoot() + "/pre",
                    filterConfiguration.getRoot() + "/post"
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return filterLoader;
    }

}
