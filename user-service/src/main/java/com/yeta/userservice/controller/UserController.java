package com.yeta.userservice.controller;

import com.yeta.userservice.model.User;
import com.yeta.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * User相关接口
 * @author YETA
 * @date 2018/11/27/12:42
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/hello")
    public String hello() {
        return userService.hello();
    }

    @GetMapping(value = "/findById")
    public User findById(@RequestParam(value = "id") Integer id) {
        return userService.findById(id);
    }
}
