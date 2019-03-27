package com.yeta.streamhello;

import com.yeta.streamhello.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableBinding(value = {Processor.class})
public class StreamHelloApplicationTests {

    @Autowired
    private Processor processor;

    @Autowired
    private MessageChannel output;

    /**
     * 基本类型消息
     */
    /*@Test
    public void contextLoads() {
        processor.output().send(MessageBuilder.withPayload("123").build());
        output.send(MessageBuilder.withPayload("456").build());
    }*/

    /**
     * 对象消息
     */
    @Test
    public void contextLoads() {
        processor.output().send(MessageBuilder.withPayload("123").build());
        output.send(MessageBuilder.withPayload("456").build());
        String message = "{\"id\":\"1\", \"name\":\"yeta\"}";
        User user = new User(1, "yeta");
        processor.output().send(MessageBuilder.withPayload(message).build());
        output.send(MessageBuilder.withPayload(user).build());
    }
}
