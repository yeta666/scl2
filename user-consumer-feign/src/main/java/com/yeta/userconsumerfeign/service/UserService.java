package com.yeta.userconsumerfeign.service;

import com.yeta.userconsumerfeign.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * User相关逻辑处理
 * @author YETA
 * @date 2018/11/28/10:23
 */
@FeignClient(name = "user-service", fallback = UserServiceFallback.class)        //指定服务名来绑定服务，指定服务降级
public interface UserService {

    @GetMapping("/hello")
    String hello();

    /**
     * 查找所有
     * @return
     */
    @GetMapping("/users")
    String findAll();

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    User findById(@PathVariable(value = "id") Integer id);

    /**
     * 新增或修改
     * @param user
     * @return
     */
    @PostMapping("/users")
    User save(@RequestBody User user);

    /**
     * 删除
     * @param id
     */
    @DeleteMapping("/users/{id}")
    void delete(@PathVariable(value = "id") Integer id);
}
