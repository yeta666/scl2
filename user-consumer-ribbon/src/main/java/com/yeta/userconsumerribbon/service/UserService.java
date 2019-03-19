package com.yeta.userconsumerribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.yeta.userconsumerribbon.exception.CommonException;
import com.yeta.userconsumerribbon.model.User;
import com.yeta.userconsumerribbon.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import rx.Observable;
import rx.Subscriber;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
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
    @CacheResult(cacheKeyMethod = "findByIdCacheKey")
    @HystrixCommand
    public CommonResponse findById(Integer id) {
        User user = restTemplate.getForObject("http://USER-SERVICE/findById?id={1}", User.class, id);
        System.out.println("调用user-service后时间 " + System.currentTimeMillis());     //证明是同步执行
        return new CommonResponse(CommonResponse.CODE1, user, CommonResponse.MESSAGE1);
    }

    private String findByIdCacheKey(Integer id) {
        return id.toString();
    }

    /**
     * 根据id查找
     * 异步执行
     * @param id
     * @return
     */
    @HystrixCommand(commandKey = "findUserById", groupKey = "UserGroup", threadPoolKey = "findUserByIdThread")
    public CommonResponse findByIdAsync(Integer id) {
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
        System.out.println("调用user-service后时间 " + System.currentTimeMillis());     //证明是异步执行
        try {
            return new CommonResponse(CommonResponse.CODE1, userFuture.get(), CommonResponse.MESSAGE1);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new CommonResponse(CommonResponse.CODE3, e.getMessage(), CommonResponse.MESSAGE3);
        }
    }

    /**
     * 根据id查找
     * 响应式执行方式
     * @param id
     * @return
     */
    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER)        //使用observe()执行方法
    //@HystrixCommand(observableExecutionMode = ObservableExecutionMode.LAZY)        //使用toObservable()执行方法
    public CommonResponse findByIdObservable(Integer id) {
        Observable<User> userObservable = Observable.create(subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    User user = restTemplate.getForObject("http://USER-SERVICE/findById?id={1}", User.class, id);
                    subscriber.onNext(user);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
        userObservable.forEach(System.out::println);
        return null;
    }

    /**
     * 新增或修改
     * @param user
     * @return
     */
    @CacheRemove(commandKey = "findById")
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
