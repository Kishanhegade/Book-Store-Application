package com.bridgelabz.bsa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String bookName;
    private String authorName;
    private String description;
    @Lob
    private byte[] logo;
    private double price;
    private Integer quantity;

}
