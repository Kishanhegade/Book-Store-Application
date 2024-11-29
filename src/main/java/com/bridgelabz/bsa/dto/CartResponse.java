package com.bridgelabz.bsa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponse {

    private long userId;
    private long bookId;
    private Double totalPrice;
    private Long quantity;

}
