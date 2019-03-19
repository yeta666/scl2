package com.yeta.userconsumerfeign.controller;

import com.yeta.userconsumerfeign.model.User;
import com.yeta.userconsumerfeign.service.UserService;
import com.yeta.userconsumerfeign.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * User相关接口
 * @author YETA
 * @date 2018/11/28/10:24
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/hello")
    public String hello(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        return userService.hello();
    }

    @GetMapping("/users")
    public CommonResponse findAll() {
        return new CommonResponse(CommonResponse.CODE1, userService.findAll(), CommonResponse.MESSAGE1);
    }

    @GetMapping("/users/{id}")
    public CommonResponse findById(@PathVariable(value = "id") Integer id) {
        return new CommonResponse(CommonResponse.CODE1, userService.findById(id), CommonResponse.MESSAGE1);
    }

    @PostMapping("/users")
    public CommonResponse save(@RequestBody User user) {
        return new CommonResponse(CommonResponse.CODE1, userService.save(user), CommonResponse.MESSAGE1);
    }

    @DeleteMapping("/users/{id}")
    public CommonResponse delete(@PathVariable(value = "id") Integer id) {
        userService.delete(id);
        return new CommonResponse(CommonResponse.CODE1, null, CommonResponse.MESSAGE1);
    }
}
