package com.bridgelabz.bsa.exception;

public class CartNotFoundByIdException extends RuntimeException {

    private String message;

    public CartNotFoundByIdException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
