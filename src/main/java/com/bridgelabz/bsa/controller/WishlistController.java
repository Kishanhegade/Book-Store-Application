package com.bridgelabz.bsa.controller;

import com.bridgelabz.bsa.responsedto.WishlistResponse;
import com.bridgelabz.bsa.service.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/wishlist/add")
    public ResponseEntity<WishlistResponse> addToWishlist(@RequestHeader("Authorization") String authHeader,
                                                @RequestParam Integer bookId) {
        String token = authHeader.substring(7);

        return ResponseEntity.status(HttpStatus.CREATED).body(wishlistService.addToWishlist(token, bookId));
    }

    @GetMapping("/wishlist")
    public ResponseEntity<List<WishlistResponse>> getWishlist(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        List<WishlistResponse> wishlist = wishlistService.getWishlist(token);
        return ResponseEntity.ok(wishlist);
    }

    @DeleteMapping("/wishlist/remove/{wishlistId}")
    public ResponseEntity<WishlistResponse> removeFromWishlist(@PathVariable Long wishlistId) {

        return ResponseEntity.ok(wishlistService.removeFromWishlist(wishlistId));
    }

}
