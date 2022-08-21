package com.hui.springboot.one.confMq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {
    final static String message = "topic.message";
    final static String messages = "topic.messages";

    @Bean
    public Queue queueMessage() {
        System.out.println("创建成功\t" + message);
        return new Queue(message);
    }

    @Bean
    public Queue queueMessages() {
        System.out.println("创建成功\t" + messages);
        return new Queue(messages);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange topicExchange) {
        System.out.println("bindingExchangeMessage \t" + queueMessage.getName());
        return BindingBuilder.bind(queueMessage).to(topicExchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange topicExchange) {
        System.out.println("bindingExchangeMessages \t" + queueMessages.getName());
        return BindingBuilder.bind(queueMessages).to(topicExchange).with("topic.#");
    }
}
