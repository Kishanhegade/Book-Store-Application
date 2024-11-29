package com.bridgelabz.bsa.mapper;

import com.bridgelabz.bsa.dto.CartResponse;
import com.bridgelabz.bsa.model.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartMapper {


    public CartResponse mapToCartResponse(Cart cart) {
        CartResponse cartResponse = new CartResponse();
        cartResponse.setBookId(cart.getBook().getBookId());
        cartResponse.setUserId(cart.getUser().getUserId());
        cartResponse.setQuantity(cart.getQuantity());
        cartResponse.setTotalPrice(cart.getTotalPrice());
        return cartResponse;
    }
}
