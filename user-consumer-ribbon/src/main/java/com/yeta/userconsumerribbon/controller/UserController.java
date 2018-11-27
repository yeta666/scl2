package com.yeta.userconsumerribbon.controller;

import com.yeta.userconsumerribbon.model.User;
import com.yeta.userconsumerribbon.service.UserService;
import com.yeta.userconsumerribbon.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;


/**
 * User相关接口
 * @author YETA
 * @date 2018/11/26/17:23
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/hello")
    public String hello(@RequestParam(value = "type") Integer type) {
        return userService.hello(type);
    }

    @GetMapping(value = "/users")
    public CommonResponse findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public CommonResponse findById(@PathVariable(value = "id") Integer id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/findByIdAsync")
    public CommonResponse findByIdAsync(@RequestParam(value = "id") Integer id) throws ExecutionException, InterruptedException {
        return userService.findByIdAsync(id);
    }

    @PostMapping(value = "/users")
    public CommonResponse save(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping(value = "/users/{id}")
    public void delete(@PathVariable(value = "id") Integer id) {
        userService.delete(id);
    }
}
