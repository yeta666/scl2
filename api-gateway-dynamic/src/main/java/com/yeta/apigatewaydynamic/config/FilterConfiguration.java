package com.yeta.apigatewaydynamic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 用来加载自定义属性的配置类
 * @author YETA
 * @date 2019/03/21/13:42
 */
@ConfigurationProperties(value = "zuul.filter")
public class FilterConfiguration {

    private String root;

    private Integer interval;

    public FilterConfiguration() {
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}
