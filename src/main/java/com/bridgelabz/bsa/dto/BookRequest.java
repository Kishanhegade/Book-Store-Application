package com.bridgelabz.bsa.dto;

import com.bridgelabz.bsa.config.ValidFile;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BookRequest {

    @NotBlank(message = "Book Name cannot be blank")
    private String bookName;
    @NotBlank(message = "Author Name cannot be blank")
    private String authorName;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;
    @NotNull(message = "Logo is mandatory")
    @ValidFile(message = "Only .jpg, .png, or .pdf files are allowed for the logo")
    private MultipartFile logo;
    @NotNull(message = "Quantity cannot be blank")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}
