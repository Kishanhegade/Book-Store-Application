package com.bridgelabz.bsa.mapper;

import com.bridgelabz.bsa.dto.CartResponse;
import com.bridgelabz.bsa.model.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public CartResponse mapToCartResponse(Cart cart) {
        CartResponse cartResponse = new CartResponse();
        cartResponse.setBook(cart.getBook());
        cartResponse.setUser(cart.getUser());
        cartResponse.setQuantity(cart.getQuantity());
        cartResponse.setTotalPrice(cart.getTotalPrice());
        return cartResponse;
    }
}
