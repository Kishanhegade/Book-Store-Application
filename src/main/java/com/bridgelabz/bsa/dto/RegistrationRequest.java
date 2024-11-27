package com.bridgelabz.bsa.dto;

import com.bridgelabz.bsa.model.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegistrationRequest {

    @NotBlank(message = "First Name is mandatory")
    private String fname;

    @NotBlank(message = "Last Name is mandatory")
    private String lname;

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should be a valid Gmail address")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$^&*._-]).{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

    @Past(message = "Date of Birth must be in the past")
    @NotBlank(message = "Date of Birth is mandatory")
    private LocalDate dob;

    @NotBlank(message = "Role is mandatory")
    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role must be either ADMIN or USER")
    private Role role;

}
