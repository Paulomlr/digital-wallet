package com.paulomlr.carteira_digital.application.usecaseimpl.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
