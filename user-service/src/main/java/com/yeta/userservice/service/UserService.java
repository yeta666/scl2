package com.yeta.userservice.service;

import com.yeta.userservice.model.User;
import com.yeta.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

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
        try {
            int sleepTime = new Random().nextInt(3000);
            System.out.println("before sleep " + System.currentTimeMillis() + " sleep time " + sleepTime);
            Thread.sleep(sleepTime);
            System.out.println("after sleep " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello, from " + registration.getServiceId() + " " + registration.getPort();
    }

    public User findById(Integer id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            User user = userOptional.orElseGet(User::new);
            System.out.println("user-service before sleep " + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("user-service after sleep " + System.currentTimeMillis());
            return user;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
