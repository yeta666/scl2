package com.yeta.apigatewaydynamicroute.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 在自定义过滤器中处理异常
 * @author YETA
 * @date 2019/03/15/17:54
 */
@Component
public class ThrowExceptionFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(ZuulFilter.class);

    /**
     * 过滤器的类型
     * pre表示在请求被路由之前执行
     * routing表示在路由请求时被调用
     * post表示在routing和error过滤器之后被调用
     * error表示处理请求时发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
        //return ROUTE_TYPE;
        //return POST_TYPE;
    }

    /**
     * 过滤器的执行顺序
     * 数字越小优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;     // run before PreDecoration
        //return SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
        //return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    /**
     * 判断该过滤器是否需要被执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        log.info("post filter");
        int a = 1 / 0;

        return null;
    }
}
