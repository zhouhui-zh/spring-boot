package com.hui.springboot.one.rabiitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HelloSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String name) {
        String context = "hello " + name;
        this.amqpTemplate.convertAndSend("hello", context);
        System.out.println("Sender1 消息发送成功\t" + context);
    }
}
