package com.hui.springboot.one.rabiitmq;

import com.hui.springboot.one.vo.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "object")
public class HelloReceiverObj {

    @RabbitHandler
    public void process(User user) {
        System.out.println("process obj 收到的消息\t" + user);
    }
}
