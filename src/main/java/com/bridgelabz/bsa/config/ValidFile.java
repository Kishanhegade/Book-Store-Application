package com.bridgelabz.bsa.config;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFile {
    String message() default "Invalid file type. Only .jpg, .png, or .pdf are allowed.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
