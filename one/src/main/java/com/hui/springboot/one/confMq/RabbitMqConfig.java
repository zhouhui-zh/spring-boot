package com.hui.springboot.one.confMq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue queue() {
        System.out.println("创建队列。。hello。");
        return new Queue("hello");
    }

    @Bean
    public Queue queue2() {
        System.out.println("创建队列。。object。");
        return new Queue("object");
    }
}
