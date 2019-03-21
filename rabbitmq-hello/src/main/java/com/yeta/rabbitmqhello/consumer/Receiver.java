package com.yeta.rabbitmqhello.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 * @author YETA
 * @date 2019/03/21/18:44
 */
@Component
@RabbitListener(queues = "hello")       //监听名为hello的队列
public class Receiver {

    @RabbitHandler      //指定消息的处理方法
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }
}
