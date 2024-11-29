package com.bridgelabz.bsa.controller;

import com.bridgelabz.bsa.dto.CartResponse;
import com.bridgelabz.bsa.security.JwtUtils;
import com.bridgelabz.bsa.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CartController {

    private CartService cartService;
    private JwtUtils jwtUtils;

    @PostMapping("/carts/add")
    public ResponseEntity<CartResponse> addToCart
            (@RequestHeader("Authorization") String authHeader,
             @RequestParam Integer bookId,
             @RequestParam Long quantity)
    {
        String token = authHeader.substring(7);
        long userId = jwtUtils.extractUserIdFromToken(token);
        CartResponse cartResponse = cartService.addToCart(userId, bookId, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartResponse);
    }

    @PatchMapping("/carts/{cartId}/update-quantity")
    public ResponseEntity<CartResponse> updateQuantity(
            @PathVariable long cartId,
            @RequestParam long quantity) {
        CartResponse cartResponse = cartService.updateQuantity(cartId, quantity);
        return ResponseEntity.status(HttpStatus.OK).body(cartResponse);
    }

    @DeleteMapping("/carts/{cartId}/remove")
    public ResponseEntity<CartResponse> removeFromCartByCartId(@PathVariable long cartId) {
        CartResponse cartResponse = cartService.removeFromCartByCartId(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cartResponse);
    }

    @DeleteMapping("/carts/user/remove-all")
    public ResponseEntity<List<CartResponse>> removeFromCartByUserId(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        long userId = jwtUtils.extractUserIdFromToken(token);
        List<CartResponse> cartResponses = cartService.removeFromCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(cartResponses);
    }

    @GetMapping("/carts/user")
    public ResponseEntity<List<CartResponse>> getAllCartItemsForUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        long userId = jwtUtils.extractUserIdFromToken(token);
        List<CartResponse> cartResponses = cartService.getAllCartItemsForUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(cartResponses);
    }

    @GetMapping("/carts")
    public ResponseEntity<List<CartResponse>> getAllCartItems() {
        List<CartResponse> cartResponses = cartService.getAllCartItems();
        return ResponseEntity.status(HttpStatus.OK).body(cartResponses);
    }
}
