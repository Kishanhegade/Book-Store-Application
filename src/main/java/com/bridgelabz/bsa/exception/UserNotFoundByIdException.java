package com.bridgelabz.bsa.exception;

public class UserNotFoundByIdException extends RuntimeException {

    private String message;

    public UserNotFoundByIdException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
