package com.paulomlr.carteira_digital.application.usecaseimpl.exceptions;

public class TransferNotFound extends RuntimeException {

    public TransferNotFound(String message) {
        super(message);
    }
}
