package com.bridgelabz.bsa.mapper;

import com.bridgelabz.bsa.dto.CartResponse;
import com.bridgelabz.bsa.model.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartMapper {

    private BookMapper bookMapper;
    private UserMapper userMapper;

    public CartResponse mapToCartResponse(Cart cart) {
        CartResponse cartResponse = new CartResponse();
        cartResponse.setBook(bookMapper.mapToBookResponse(cart.getBook()));
        cartResponse.setUser(userMapper.mapToUserResponse(cart.getUser()));
        cartResponse.setQuantity(cart.getQuantity());
        cartResponse.setTotalPrice(cart.getTotalPrice());
        return cartResponse;
    }
}
