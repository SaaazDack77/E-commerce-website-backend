package com.example.backendprojem.repository;

import com.example.backendprojem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
} 