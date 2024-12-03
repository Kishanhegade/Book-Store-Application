package com.bridgelabz.bsa.exception;

import com.bridgelabz.bsa.utility.CustomFieldError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

public class FieldErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ObjectError> allErrors = ex.getAllErrors();

        List<CustomFieldError> customFieldErrors = allErrors.stream().map(objectError -> {
            FieldError fieldError = (FieldError) objectError;
            return CustomFieldError.create(fieldError.getField(), fieldError.getDefaultMessage());
        }).toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customFieldErrors);
    }
}
