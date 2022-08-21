package com.hui.springboot.one.confMq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig {
    @Bean("aMessage")
    public Queue AMessage() {
        System.out.println("创建成功 fanout.A");
        return new Queue("fanout.A");
    }

    @Bean("bMessage")
    public Queue BMessage() {
        System.out.println("创建成功 fanout.B");
        return new Queue("fanout.B");
    }

    @Bean("cMessage")
    public Queue CMessage() {

        System.out.println("创建成功 fanout.C");
        return new Queue("fanout.C");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA(@Qualifier(value = "aMessage") Queue AMessage, FanoutExchange fanoutExchange) {
        System.out.println("bindingExchangeC \t" + AMessage.getName());
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(@Qualifier(value = "bMessage") Queue BMessage, FanoutExchange fanoutExchange) {
        System.out.println("bindingExchangeC \t" + BMessage.getName());
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(@Qualifier(value = "cMessage") Queue CMessage, FanoutExchange fanoutExchange) {
        System.out.println("bindingExchangeC \t" + CMessage.getName());
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
}
