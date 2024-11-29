package com.bridgelabz.bsa.dto;

import com.bridgelabz.bsa.model.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    @NotNull(message = "Book ID cannot be null")
    @Positive(message = "Book ID must be a positive number")
    private long bookId;

    @NotNull(message = "Address cannot be null")
    @Valid // To validate nested objects
    private Address address;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private double price;


}
