package filter.pre

import com.netflix.zuul.ZuulFilter

import org.slf4j.Logger
import org.slf4j.LoggerFactory


class PreFilter extends ZuulFilter {

    Logger log = LoggerFactory.getLogger(PreFilter.class)

    @Override
    String filterType() {
        return "pre"
    }

    @Override
    int filterOrder() {
        return 1000
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {
        log.info("pre")
        return null
    }
}
