package com.example.microservice.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Mesajı kuyruk üzerinden gönderiyoruz
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("productQueue", message);
    }
}
