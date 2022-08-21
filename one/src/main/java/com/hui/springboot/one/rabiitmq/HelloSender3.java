package com.hui.springboot.one.rabiitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloSender3 {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send1() {
        String context = "hi , i am message 1";
        this.amqpTemplate.convertAndSend("exchange", "topic.message", context);
        System.out.println("Sender1 消息发送成功\t" + context);
    }

    public void send2() {
        String context = "hi , i am message 2";
        this.amqpTemplate.convertAndSend("exchange", "topic.messages", context);
        System.out.println("Sender1 消息发送成功\t" + context);
    }
}
