package com.bridgelabz.bsa.service;

import com.bridgelabz.bsa.exception.BookNotFoundByIdException;
import com.bridgelabz.bsa.exception.UserNotFoundByIdException;
import com.bridgelabz.bsa.exception.WishlistNotFoundByIdException;
import com.bridgelabz.bsa.mapper.WishlistMapper;
import com.bridgelabz.bsa.model.Book;
import com.bridgelabz.bsa.model.User;
import com.bridgelabz.bsa.model.Wishlist;
import com.bridgelabz.bsa.repository.BookRepository;
import com.bridgelabz.bsa.repository.UserRepository;
import com.bridgelabz.bsa.repository.WishlistRepository;
import com.bridgelabz.bsa.responsedto.WishlistResponse;
import com.bridgelabz.bsa.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepo;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepo;
    private final BookRepository bookRepo;
    private final WishlistMapper wishlistMapper;

    public WishlistResponse addToWishlist(String token, Integer bookId) {
        long userId = jwtUtils.extractUserIdFromToken(token);
        User user = userRepo.findById(userId)
                .orElseThrow(()->new UserNotFoundByIdException("Failed to find user by Id"));
        Book book = bookRepo.findById(bookId).orElseThrow(()->new BookNotFoundByIdException("Failed to find book"));
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setBook(book);
        wishlist.setAddedDate(LocalDate.now());
        wishlistRepo.save(wishlist);
        return wishlistMapper.mapToWishlistResponse(wishlist);
    }

    public List<WishlistResponse> getWishlist(String token) {
        long userId = jwtUtils.extractUserIdFromToken(token);
        return wishlistRepo.findAllByUserId(userId).stream()
                .map(wishlistMapper::mapToWishlistResponse).toList();
    }


    public WishlistResponse removeFromWishlist(Long wishlistId) {
        return wishlistRepo.findById(wishlistId)
                .map(wishlist -> {
                    wishlistRepo.delete(wishlist);
                    return wishlistMapper.mapToWishlistResponse(wishlist);
                }).orElseThrow(()->new WishlistNotFoundByIdException("Failed to find wishlist"));
    }
}
