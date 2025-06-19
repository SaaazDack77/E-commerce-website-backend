package com.example.backendprojem.service;

import com.example.backendprojem.model.Cart;
import com.example.backendprojem.model.CartItem;
import com.example.backendprojem.model.Product;
import com.example.backendprojem.repository.CartRepository;
import com.example.backendprojem.repository.CartItemRepository;
import com.example.backendprojem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    @Transactional
    public Cart addToCart(Long cartId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setPrice(product.getPrice());
            cart.getItems().add(newItem);
        }

        updateCartTotal(cart);
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart updateCartItemQuantity(Long cartId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        item.setQuantity(quantity);
        updateCartTotal(cart);
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        updateCartTotal(cart);
        return cartRepository.save(cart);
    }

    private void updateCartTotal(Cart cart) {
        double total = cart.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        cart.setTotalAmount(total);
    }
} 