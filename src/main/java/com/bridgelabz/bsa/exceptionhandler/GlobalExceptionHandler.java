package com.bridgelabz.bsa.exceptionhandler;

import com.bridgelabz.bsa.exception.BookNotFoundByIdException;
import com.bridgelabz.bsa.exception.CartNotFoundByIdException;
import com.bridgelabz.bsa.exception.InvalidRequestException;
import com.bridgelabz.bsa.exception.UserNotFoundByIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundByIdException.class)
    public ResponseEntity<String> handleUserNotFoundById(UserNotFoundByIdException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BookNotFoundByIdException.class)
    public ResponseEntity<String> handleBookNotFoundById(BookNotFoundByIdException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CartNotFoundByIdException.class)
    public ResponseEntity<String> handleCartNotFoundById(CartNotFoundByIdException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<String> handleInvalidRequestException(InvalidRequestException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
