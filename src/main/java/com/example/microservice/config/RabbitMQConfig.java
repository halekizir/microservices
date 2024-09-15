package com.example.microservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue myQueue() {
        return new Queue("myQueue", true); // Kuyruğun kalıcı olmasını sağlar
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("myExchange");
    }

    @Bean
    public Binding binding(Queue myQueue, TopicExchange exchange) {
        return BindingBuilder.bind(myQueue).to(exchange).with("myQueue");
    }
}
