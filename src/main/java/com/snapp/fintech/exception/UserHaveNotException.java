package com.snapp.fintech.exception;

public class UserHaveNotException extends RuntimeException {

    public UserHaveNotException(String errorMessage) {
        super(errorMessage);
    }
}
