package com.example.backendprojem.repository;

import com.example.backendprojem.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
} 