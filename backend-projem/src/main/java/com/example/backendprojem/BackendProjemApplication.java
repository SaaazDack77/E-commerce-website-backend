package com.example.backendprojem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.backendprojem")
@EntityScan("com.example.backendprojem.model")
@EnableJpaRepositories("com.example.backendprojem.repository")
public class BackendProjemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendProjemApplication.class, args);
    }
} 