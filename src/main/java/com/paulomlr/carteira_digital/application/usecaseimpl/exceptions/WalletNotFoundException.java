package com.paulomlr.carteira_digital.application.usecaseimpl.exceptions;

public class WalletNotFoundException extends RuntimeException {

    public WalletNotFoundException(String message) {
        super(message);
    }
}
