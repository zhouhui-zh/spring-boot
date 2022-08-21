package com.hui.springboot.one.rabiitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.C")
public class HelloReceiverFanoutC {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("HelloReceiverFanoutC 收到的消息\t" + msg);
    }
}
