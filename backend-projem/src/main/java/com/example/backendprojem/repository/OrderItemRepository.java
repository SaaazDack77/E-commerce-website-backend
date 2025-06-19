package com.example.backendprojem.repository;

import com.example.backendprojem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
} 