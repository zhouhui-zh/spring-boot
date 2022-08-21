package com.hui.springboot.one;

import com.hui.springboot.one.rabiitmq.HelloSender3;
import com.hui.springboot.one.rabiitmq.HelloSenderFanout;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {
    @Autowired
    private HelloSender3 helloSender3;
    @Autowired
    private HelloSenderFanout helloSenderFanout;

    @Test
    public void send1() {
        helloSender3.send1();
        helloSender3.send2();
    }

    @Test
    public void fanout() {
        helloSenderFanout.send();
    }
}
