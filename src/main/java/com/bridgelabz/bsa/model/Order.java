package com.bridgelabz.bsa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDate orderDate;
    private Double price;
    private long quantity;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Address address;
    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;
    private boolean cancel;

}
