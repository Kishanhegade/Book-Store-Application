package com.bridgelabz.bsa.service;

import com.bridgelabz.bsa.dto.CartResponse;
import com.bridgelabz.bsa.exception.BookNotFoundByIdException;
import com.bridgelabz.bsa.exception.CartNotFoundByIdException;
import com.bridgelabz.bsa.exception.InvalidRequestException;
import com.bridgelabz.bsa.exception.UserNotFoundByIdException;
import com.bridgelabz.bsa.mapper.CartMapper;
import com.bridgelabz.bsa.model.Book;
import com.bridgelabz.bsa.model.Cart;
import com.bridgelabz.bsa.model.User;
import com.bridgelabz.bsa.repository.BookRepository;
import com.bridgelabz.bsa.repository.CartRepository;
import com.bridgelabz.bsa.repository.UserRepository;
import com.bridgelabz.bsa.security.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {

    private CartRepository cartRepository;
    private CartMapper cartMapper;
    private JwtUtils jwtUtils;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    @Transactional
    public CartResponse addToCart(String token, Integer bookId, Long quantity) {
        if (quantity <= 0) {
            throw new InvalidRequestException("Quantity must be greater than zero");
        }

        Long userId = jwtUtils.extractUserIdFromToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("User not found with ID: " + userId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundByIdException("Book not found with ID: " + bookId));

        Cart cart = cartRepository.findByUserAndBook(user, book)
                .map(existingCart -> {
                    existingCart.setQuantity(existingCart.getQuantity() + quantity);
                    existingCart.setTotalPrice(existingCart.getQuantity() * book.getPrice());
                    return existingCart;
                }).orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setBook(book);
                    newCart.setQuantity(quantity);
                    newCart.setTotalPrice(quantity * book.getPrice());
                    return newCart;
                });

        cartRepository.save(cart);
        return cartMapper.mapToCartResponse(cart);

    }

    @Transactional
    public CartResponse removeFromCartByCartId(long cartId) {
        return cartRepository.findById(cartId)
                .map(cart->{
                    cartRepository.delete(cart);
                    return cartMapper.mapToCartResponse(cart);
                }).orElseThrow(()->new CartNotFoundByIdException("Unable to remove from cart"));
    }

    @Transactional
    public List<CartResponse> removeFromCartByUserId(String token) {
        Long userId = jwtUtils.extractUserIdFromToken(token);
        return cartRepository.findAllByUserId(userId)
                .stream()
                .map(cart -> {
                    cartRepository.delete(cart);
                    return cartMapper.mapToCartResponse(cart);
                }).toList();
    }

    public CartResponse updateQuantity(long cartId, long quantity) {
        if (quantity <= 0) {
            throw new InvalidRequestException("Quantity must be greater than zero");
        }
        return cartRepository.findById(cartId)
                .map(cart -> {
                    cart.setQuantity(quantity);
                    cart = cartRepository.save(cart);
                    return cartMapper.mapToCartResponse(cart);
                }).orElseThrow(()->new CartNotFoundByIdException("Unable to update quantity"));
    }

    public List<CartResponse> getAllCartItemsForUser(String token) {
        long userId = jwtUtils.extractUserIdFromToken(token);
        return cartRepository.findAllByUserId(userId)
                .stream().map(
                        cart -> {
                            return cartMapper.mapToCartResponse(cart);
                        }
                ).toList();
    }

    public List<CartResponse> getAllCartItems() {
        return cartRepository.findAll()
                .stream().map(cart -> {
                    return cartMapper.mapToCartResponse(cart);
                }).toList();
    }
}
