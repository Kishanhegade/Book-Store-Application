package com.bridgelabz.bsa.controller;

import com.bridgelabz.bsa.dto.CartResponse;
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
    public ResponseEntity<CartResponse> removeFromCartByCartId(@PathVariable long cartId) {
        CartResponse cartResponse = cartService.removeFromCartByCartId(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cartResponse);
    }

    @DeleteMapping("/carts")
    public ResponseEntity<List<CartResponse>> removeFromCartByUserId(@RequestHeader("Authorization") String token) {
        List<CartResponse> cartResponses = cartService.removeFromCartByUserId(token);
        return ResponseEntity.status(HttpStatus.OK).body(cartResponses);
    }

    @GetMapping("/carts")
    public ResponseEntity<List<CartResponse>> getAllCartItemsForUser(@RequestHeader("Authorization") String token) {
        List<CartResponse> cartResponses = cartService.getAllCartItemsForUser(token);
        return ResponseEntity.status(HttpStatus.OK).body(cartResponses);
    }

    @GetMapping("")
    public ResponseEntity<List<CartResponse>> getAllCartItems() {
        List<CartResponse> cartResponses = cartService.getAllCartItems();
        return ResponseEntity.status(HttpStatus.OK).body(cartResponses);
    }
}
