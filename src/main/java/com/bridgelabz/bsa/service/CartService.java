package com.bridgelabz.bsa.service;

import com.bridgelabz.bsa.dto.CartResponse;
import com.bridgelabz.bsa.exception.CartNotFoundByIdException;
import com.bridgelabz.bsa.mapper.CartMapper;
import com.bridgelabz.bsa.model.Book;
import com.bridgelabz.bsa.model.Cart;
import com.bridgelabz.bsa.model.User;
import com.bridgelabz.bsa.repository.BookRepository;
import com.bridgelabz.bsa.repository.CartRepository;
import com.bridgelabz.bsa.repository.UserRepository;
import com.bridgelabz.bsa.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {

    private CartRepository cartRepository;
    private CartMapper cartMapper;
    private JwtUtils jwtUtils;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    public CartResponse addToCart(String token, Integer bookId, Long quantity) {
        Long userId = jwtUtils.extractUserIdFromToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));

        Cart cart1 = cartRepository.findByUserAndBook(user, book)
                .map(cart -> {
                    cart.setTotalPrice(book.getPrice() * quantity);
                    cart = cartRepository.save(cart);
                    return cart;
                }).orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    cart.setBook(book);
                    cart.setQuantity(quantity);
                    cart.setTotalPrice(quantity * book.getPrice());
                    cart = cartRepository.save(cart);
                    return cart;
                });

        return cartMapper.mapToCartResponse(cart1);

    }

    public CartResponse removeFromCart(long cartId) {
        return cartRepository.findById(cartId)
                .map(cart->{
                    cartRepository.delete(cart);
                    return cartMapper.mapToCartResponse(cart);
                }).orElseThrow(()->new CartNotFoundByIdException("Unable to remove from cart"));
    }
}
