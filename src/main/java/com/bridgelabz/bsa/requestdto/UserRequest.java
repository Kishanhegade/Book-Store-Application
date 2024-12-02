package com.bridgelabz.bsa.requestdto;

import com.bridgelabz.bsa.model.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "First Name is mandatory")
    private String fname;

    @NotBlank(message = "Last Name is mandatory")
    private String lname;

    @Past(message = "Date of Birth must be in the past")
    @NotBlank(message = "Date of Birth is mandatory")
    private LocalDate dob;

    @NotBlank(message = "Role is mandatory")
    private Role role;

}
