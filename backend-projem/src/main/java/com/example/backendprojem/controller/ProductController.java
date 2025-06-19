package com.example.backendprojem.controller;

import com.example.backendprojem.model.Product;
import com.example.backendprojem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            System.out.println("Gelen ürün verileri:");
            System.out.println("Name: " + product.getName());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Stock: " + product.getStock());
            System.out.println("CategoryId: " + product.getCategoryId());
            System.out.println("ImageBase64: " + (product.getImageBase64() != null ? "Var" : "Yok"));

            Product savedProduct = productService.saveProduct(product);
            System.out.println("Ürün başarıyla kaydedildi. ID: " + savedProduct.getId());
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            System.err.println("Ürün kaydedilirken hata: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.getProductById(id)
                .map(existingProduct -> {
                    try {
                        existingProduct.setName(product.getName());
                        existingProduct.setDescription(product.getDescription());
                        existingProduct.setPrice(product.getPrice());
                        existingProduct.setStock(product.getStock());
                        existingProduct.setCategoryId(product.getCategoryId());
                        existingProduct.setImageBase64(product.getImageBase64());

                        return ResponseEntity.ok(productService.saveProduct(existingProduct));
                    } catch (Exception e) {
                        System.err.println("Ürün güncellenirken hata: " + e.getMessage());
                        e.printStackTrace();
                        return ResponseEntity.badRequest().<Product>build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> {
                    try {
                        productService.deleteProduct(id);
                        return ResponseEntity.ok().<Void>build();
                    } catch (Exception e) {
                        System.err.println("Ürün silinirken hata: " + e.getMessage());
                        e.printStackTrace();
                        return ResponseEntity.badRequest().<Void>build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 