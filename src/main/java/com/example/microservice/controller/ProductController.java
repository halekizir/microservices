package com.example.microservice.controller;

import com.example.microservice.model.Product;
import com.example.microservice.repository.ProductRepository;
import com.example.microservice.service.ProductProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductProducer productProducer; // RabbitMQ Producer servisi

    // Tüm ürünleri getir
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Yeni ürün oluştur
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);

        // Ürün oluşturulduktan sonra mesaj RabbitMQ'ya gönderilir
        productProducer.sendMessage("Ürün eklendi: " + savedProduct.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // RabbitMQ'ya manuel mesaj gönder
    @PostMapping("/send/{message}")
    public String sendMessageToRabbitMQ(@PathVariable String message) {
        productProducer.sendMessage(message);
        return "Message sent to RabbitMQ: " + message;
    }
}
