package com.bridgelabz.bsa.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BookResponse {

    private Integer bookId;
    private String bookName;
    private String authorName;
    private String description;
    private MultipartFile logo;
    private Double price;
    private Integer quantity;
}
