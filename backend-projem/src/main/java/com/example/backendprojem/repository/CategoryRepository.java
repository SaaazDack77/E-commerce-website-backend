package com.example.backendprojem.repository;

import com.example.backendprojem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
} 