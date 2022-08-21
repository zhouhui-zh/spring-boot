package com.hui.springboot.one.rabiitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloSenderFanout {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String context = "hi ,fanout msg ";
        this.amqpTemplate.convertAndSend("fanoutExchange", "", context);
        System.out.println("HelloSenderFanout 消息发送成功\t" + context);
    }

}
