package com.yeta.streamhello.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeta.streamhello.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.GenericMessage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YETA
 * @date 2019/03/26/16:21
 */
@EnableBinding(value = {Processor.class})      //指定一个或多个定义了@Input或@Output注解的接口，以此实现对消息通道的绑定
public class MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);

    /**
     * 基本类型消息
     * @param user
     */
    /*@StreamListener(Processor.INPUT)     //将被修饰的方法注册为消息中间件上数据流的事件监听器
    public void input(Object payload) {
        log.info("input: " + payload);
    }*/

    /**
     * 对象消息
     * @param user
     */
    /*@StreamListener(Processor.INPUT)
    public void input(User user) {
        log.info("input: " + user);
    }*/

    /**
     * 消息反馈
     * @param user
     * @return
     */
    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public User inputAndOutput(User user) {
        log.info("app1: " + user);
        return new User(2, "ray");
    }

    /**
     * 基本类型消息
     */
    /*@ServiceActivator(inputChannel = Processor.INPUT)
    public void input(String payload) {
        log.info("input: " + payload);
    }

    @Bean
    @InboundChannelAdapter(value = Processor.OUTPUT, poller = @Poller(fixedDelay = "2000"))     //2秒轮询执行
    public MessageSource<Date> timerMessageSource() {
        return () -> new GenericMessage<>(new Date());
    }

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)      //消息转换
    public Object transform(Date message) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message);
    }*/

    /**
     * 对象消息
     */
    /*@ServiceActivator(inputChannel = Processor.INPUT)
    public void input(User user) {
        log.info("input: " + user);
    }

    @Bean
    @InboundChannelAdapter(value = Processor.OUTPUT, poller = @Poller(fixedDelay = "2000"))
    public MessageSource<String> timerMessageSource() {
        String message = "{\"id\":\"1\", \"name\":\"yeta\"}";
        return () -> new GenericMessage<>(message);
    }

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public Object transform(String message) {
        try {
            return new ObjectMapper().readValue(message, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /**
     * 消息反馈
     * @param user
     */
    @ServiceActivator(inputChannel = Processor.INPUT)
    public void input(User user) {
        log.info("app2: " + user);
    }

    @Bean
    @InboundChannelAdapter(value = Processor.OUTPUT, poller = @Poller(fixedDelay = "2000"))
    public MessageSource<String> timerMessageSource() {
        String message = "{\"id\":\"1\", \"name\":\"yeta\"}";
        return () -> new GenericMessage<>(message);
    }

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public Object transform(String message) {
        try {
            return new ObjectMapper().readValue(message, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


