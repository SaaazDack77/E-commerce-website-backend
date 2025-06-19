package com.example.backendprojem.service;

import com.example.backendprojem.model.Order;
import com.example.backendprojem.model.OrderItem;
import com.example.backendprojem.model.Product;
import com.example.backendprojem.repository.OrderRepository;
import com.example.backendprojem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public Order saveOrder(Order order) {
        try {
            logger.info("Sipariş kaydediliyor: {}", order);
            
            // Siparişteki her ürün için stok miktarını güncelle
            for (OrderItem item : order.getItems()) {
                // Ürünü veritabanından al
                Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Ürün bulunamadı: " + item.getProduct().getId()));
                
                logger.info("Ürün stok kontrolü: {} - Mevcut stok: {}", product.getName(), product.getStock());
                
                int newStock = product.getStock() - item.getQuantity();
                if (newStock < 0) {
                    String errorMessage = String.format("Yetersiz stok: %s (Mevcut: %d, İstenen: %d)", 
                        product.getName(), product.getStock(), item.getQuantity());
                    logger.error(errorMessage);
                    throw new RuntimeException(errorMessage);
                }
                
                product.setStock(newStock);
                productService.saveProduct(product);
                logger.info("Ürün stok güncellendi: {} - Yeni stok: {}", product.getName(), newStock);
                
                // OrderItem'a ürünü set et
                item.setProduct(product);
            }
            
            Order savedOrder = orderRepository.save(order);
            logger.info("Sipariş başarıyla kaydedildi: {}", savedOrder.getId());
            return savedOrder;
        } catch (Exception e) {
            logger.error("Sipariş kaydedilirken hata oluştu", e);
            throw e;
        }
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
} 