package com.example.microservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ProductConsumer {

    // productQueue'daki mesajları dinliyoruz
    @RabbitListener(queues = "productQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
