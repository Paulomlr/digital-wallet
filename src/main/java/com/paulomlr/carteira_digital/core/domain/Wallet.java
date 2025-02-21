package com.paulomlr.carteira_digital.core.domain;

import com.paulomlr.carteira_digital.core.domain.exceptions.InsufficientBalanceException;
import com.paulomlr.carteira_digital.core.domain.exceptions.InvalidTransactionAmountException;

import java.math.BigDecimal;
import java.util.UUID;

public class Wallet {

    private UUID walletId;
    private String walletCode;
    private BigDecimal balance;

    public Wallet(UUID walletId, String walletCode, BigDecimal balance) {
        this.walletId = walletId;
        this.walletCode = walletCode;
        this.balance = balance;
    }

    public Wallet(String walletCode) {
        this.walletCode = walletCode;
        this.balance = BigDecimal.ZERO;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public String getWalletCode() {
        return walletCode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void addBalance(BigDecimal amount) {
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionAmountException("The value to be added must be greater than zero!");
        }

        this.balance = this.balance.add(amount);
    }

    public void subtractBalance(BigDecimal amount) {
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionAmountException("The value to be subtracted must be greater than zero!");
        }
        if(this.balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance to perform the operation!");
        }

        this.balance = this.balance.subtract(amount);
    }
}
