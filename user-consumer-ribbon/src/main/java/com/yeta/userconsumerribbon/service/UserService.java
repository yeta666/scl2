package com.yeta.userconsumerribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.yeta.userconsumerribbon.exception.CommonException;
import com.yeta.userconsumerribbon.model.User;
import com.yeta.userconsumerribbon.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * User相关逻辑处理
 * @author YETA
 * @date 2018/11/26/19:25
 */
@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 通过RestTemplate对USER-SERVICE服务提供的/hello接口进行调用
     * fallbackMethod定义服务降级处理
     * ignoreExceptions定义能够抛出的异常，否则将调用fallbackMethod
     * @return
     */
    @HystrixCommand(fallbackMethod = "helloFallback", ignoreExceptions = CommonException.class)
    public String hello(Integer type) {
        if (type == 1) {
            throw new CommonException("exception");     //可以抛出允许抛出的异常
        } else if (type == 2) {
            int a = 1 / 0;      //不能抛出该异常，因为未被允许，进入了fallbackMethod
        }
        return restTemplate.getForEntity("http://USER-SERVICE/hello", String.class).getBody();
    }

    private String helloFallback(Integer type, Throwable e) {
        //进入fallbackMethod的异常可以在这里做一些处理，只需增加Throwable参数
        if (e != null) {
            e.printStackTrace();
            return e.getMessage();
        } else {
            return "error";
        }
    }

    /**
     * 查找所有
     * @return
     */
    @HystrixCommand
    public CommonResponse findAll() {
        String result = restTemplate.getForEntity("http://USER-SERVICE/users", String.class).getBody();
        return new CommonResponse(CommonResponse.CODE1, result, CommonResponse.MESSAGE1);
    }

    /**
     * 根据id查找
     * 同步执行
     * @param id
     * @return
     */
    @HystrixCommand
    public CommonResponse findById(Integer id) {
        User user = restTemplate.getForObject("http://USER-SERVICE/users/{1}", User.class, id);
        return new CommonResponse(CommonResponse.CODE1, user, CommonResponse.MESSAGE1);
    }

    /**
     * 根据id查找
     * 异步执行
     * @param id
     * @return
     */
    public CommonResponse findByIdAsync(Integer id) throws ExecutionException, InterruptedException {
        Future<User> userFuture = new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://USER-SERVICE/findById?id={1}", User.class, id);
            }

            @Override
            public User get() throws UnsupportedOperationException {
                return invoke();
            }
        };
        System.out.println(System.currentTimeMillis());     //证明是异步执行
        return new CommonResponse(CommonResponse.CODE1, userFuture.get(), CommonResponse.MESSAGE1);
    }

    /**
     * 新增或修改
     * @param user
     * @return
     */
    @HystrixCommand
    public CommonResponse save(User user) {
        User sUser = restTemplate.postForObject("http://USER-SERVICE/users", user, User.class);
        return new CommonResponse(CommonResponse.CODE1, sUser, CommonResponse.MESSAGE1);
    }

    /**
     * 删除
     * @param id
     */
    @HystrixCommand
    public CommonResponse delete(Integer id) {
        restTemplate.delete("http://USER-SERVICE/users", id);
        return new CommonResponse(CommonResponse.CODE1, null, CommonResponse.MESSAGE1);
    }
}
