package com.yeta.userconsumerfeign.service;

import com.yeta.userconsumerfeign.model.User;
import org.springframework.stereotype.Component;

/**
 * User相关逻辑处理服务降级配置
 * @author YETA
 * @date 2018/11/28/11:47
 */
@Component
public class UserServiceFallback implements UserService {

    @Override
    public String hello() {
        return "fallback";
    }

    @Override
    public String findAll() {
        return "fallback";
    }

    @Override
    public User findById(Integer id) {
        return new User();
    }

    @Override
    public User save(User user) {
        return new User();
    }

    @Override
    public void delete(Integer id) {
    }
}
