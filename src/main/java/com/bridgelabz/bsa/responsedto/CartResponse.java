package com.bridgelabz.bsa.responsedto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponse {

    private long cartId;
    private long userId;
    private long bookId;
    private Double totalPrice;
    private Long quantity;

}
