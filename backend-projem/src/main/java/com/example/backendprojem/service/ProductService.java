package com.example.backendprojem.service;

import com.example.backendprojem.model.Product;
import com.example.backendprojem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findByIsDeletedFalse();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id)
                .filter(product -> !product.getIsDeleted());
    }

    public Product saveProduct(Product product) {
        System.out.println("Kaydedilen ürün: " + product);
        System.out.println("Kaydedilen resim: " + (product.getImageBase64() != null ? product.getImageBase64().substring(0, 100) + "..." : "Resim yok"));
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.softDelete(id);
    }
} 