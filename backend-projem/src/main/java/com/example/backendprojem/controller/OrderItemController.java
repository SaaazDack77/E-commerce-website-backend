package com.example.backendprojem.controller;

import com.example.backendprojem.model.OrderItem;
import com.example.backendprojem.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id);
    }

    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.saveOrderItem(orderItem);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
    }
} 