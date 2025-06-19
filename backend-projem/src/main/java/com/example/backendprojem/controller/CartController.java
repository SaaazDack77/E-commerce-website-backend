package com.example.backendprojem.controller;

import com.example.backendprojem.model.Cart;
import com.example.backendprojem.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
        Cart cart = cartService.getCart(cartId);
        return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<Cart> addToCart(
            @PathVariable Long cartId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {
        Cart cart = cartService.addToCart(cartId, productId, quantity);
        return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{cartId}/remove/{cartItemId}")
    public ResponseEntity<Cart> removeFromCart(
            @PathVariable Long cartId,
            @PathVariable Long cartItemId) {
        Cart cart = cartService.removeFromCart(cartId, cartItemId);
        return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{cartId}/update/{cartItemId}")
    public ResponseEntity<Cart> updateCartItemQuantity(
            @PathVariable Long cartId,
            @PathVariable Long cartItemId,
            @RequestParam Integer quantity) {
        Cart cart = cartService.updateCartItemQuantity(cartId, cartItemId, quantity);
        return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.badRequest().build();
    }
} 