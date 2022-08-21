package com.hui.springboot.one.rabiitmq;

import com.hui.springboot.one.vo.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloSenderObj {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String name) {
        User user = new User();
        user.setUsername(name);
        this.amqpTemplate.convertAndSend("object", user);
        System.out.println("SenderObj 消息发送成功\t" + user);
    }
}
