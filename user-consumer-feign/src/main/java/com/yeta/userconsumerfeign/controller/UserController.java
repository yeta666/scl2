package com.yeta.userconsumerfeign.controller;

import com.yeta.userconsumerfeign.model.User;
import com.yeta.userconsumerfeign.service.UserSerice;
import com.yeta.userconsumerfeign.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User相关接口
 * @author YETA
 * @date 2018/11/28/10:24
 */
@RestController
public class UserController {

    @Autowired
    private UserSerice userSerice;

    @GetMapping(value = "/hello")
    public String hello() {
        return userSerice.hello();
    }

    @GetMapping("/users")
    public CommonResponse findAll() {
        return new CommonResponse(CommonResponse.CODE1, userSerice.findAll(), CommonResponse.MESSAGE1);
    }

    @GetMapping("/users/{id}")
    public CommonResponse findById(@PathVariable(value = "id") Integer id) {
        return new CommonResponse(CommonResponse.CODE1, userSerice.findById(id), CommonResponse.MESSAGE1);
    }

    @PostMapping("/users")
    public CommonResponse save(@RequestBody User user) {
        return new CommonResponse(CommonResponse.CODE1, userSerice.save(user), CommonResponse.MESSAGE1);
    }

    @DeleteMapping("/users/{id}")
    public CommonResponse delete(@PathVariable(value = "id") Integer id) {
        userSerice.delete(id);
        return new CommonResponse(CommonResponse.CODE1, null, CommonResponse.MESSAGE1);
    }
}
