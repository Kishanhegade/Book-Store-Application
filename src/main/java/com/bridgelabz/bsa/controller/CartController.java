package com.bridgelabz.bsa.controller;

import com.bridgelabz.bsa.dto.CartResponse;
import com.bridgelabz.bsa.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CartController {

    private CartService cartService;

    @PostMapping("/carts/add")
    public ResponseEntity<CartResponse> addToCart
            (@RequestHeader("Authorization") String token,
                                                  @RequestParam Integer bookId,
             @RequestParam Long quantity)
    {
        CartResponse cartResponse = cartService.addToCart(token, bookId, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartResponse);
    }

    @DeleteMapping("/carts/{cartId}")
    public ResponseEntity<CartResponse> removeFromCart(@PathVariable long cartId) {
        CartResponse cartResponse = cartService.removeFromCart(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cartResponse);
    }
}
