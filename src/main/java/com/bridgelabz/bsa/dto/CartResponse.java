package com.bridgelabz.bsa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponse {

    private UserResponse user;
    private BookResponse book;
    private Double totalPrice;
    private Long quantity;

}
