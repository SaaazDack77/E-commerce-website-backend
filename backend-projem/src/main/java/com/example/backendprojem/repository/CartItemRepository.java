package com.example.backendprojem.repository;

import com.example.backendprojem.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
} 