package com.yeta.userservice.service;

import com.yeta.userservice.model.User;
import com.yeta.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User相关逻辑处理
 * @author YETA
 * @date 2018/11/27/12:42
 */
@Service
public class UserService {

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private Registration registration;

    @Autowired
    private UserRepository userRepository;

    public String hello() {
        return "hello, from " + registration.getServiceId() + " " + registration.getPort();
    }

    public User findById(Integer id) throws InterruptedException {
        System.out.println(System.currentTimeMillis());     //证明是异步执行
        Thread.sleep(2000);
        Optional<User> userOptional = userRepository.findById(id);
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            user = new User();
        }
        System.out.println(System.currentTimeMillis());     //证明是异步执行
        return user;
    }
}
