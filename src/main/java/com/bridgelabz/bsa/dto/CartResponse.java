package com.bridgelabz.bsa.dto;

import com.bridgelabz.bsa.model.Book;
import com.bridgelabz.bsa.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponse {

    private User user;
    private Book book;
    private Double totalPrice;
    private Long quantity;

}
