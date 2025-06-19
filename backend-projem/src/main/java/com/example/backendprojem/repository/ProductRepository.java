package com.example.backendprojem.repository;

import com.example.backendprojem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByIsDeletedFalse();
    
    @Modifying
    @Query("UPDATE Product p SET p.isDeleted = true WHERE p.id = :id")
    void softDelete(@Param("id") Long id);
} 