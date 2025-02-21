package com.paulomlr.carteira_digital.application.usecaseimpl.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
