package com.paulomlr.carteira_digital.application.usecaseimpl.exceptions;

public class SameWalletTransferException extends RuntimeException {
    public SameWalletTransferException(String message) {
        super(message);
    }
}
