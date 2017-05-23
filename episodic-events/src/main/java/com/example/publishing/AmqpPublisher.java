package com.example.publishing;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpPublisher {
    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange("my-exchange");
    }

    @Bean
    public Queue queue() {
        return new Queue("my-publish-queue");
    }

    @Bean
    public Binding declareBinding() {
        return BindingBuilder.bind(queue()).to(appExchange()).with("my-routing-key");
    }
}