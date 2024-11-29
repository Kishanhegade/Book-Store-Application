package com.bridgelabz.bsa.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OrderResponse {

    private long orderId;
    private LocalDate orderDate;
    private double price;
    private long quantity;
    private long userId;
    private long bookId;
    private String address;
}
