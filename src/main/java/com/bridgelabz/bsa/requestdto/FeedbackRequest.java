package com.bridgelabz.bsa.requestdto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackRequest {

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    private int rating;

    @NotBlank(message = "Comment must not be blank")
    @Size(max = 500, message = "Comment must not exceed 500 characters")
    private String comment;
}
