package com.paulomlr.carteira_digital.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transfer {

    private UUID transferId;
    private LocalDateTime transferDate;
    private BigDecimal amount;
    private Wallet originWallet;
    private Wallet destinationWallet;

    public Transfer(UUID transferId, LocalDateTime transferDate, BigDecimal amount, Wallet originWallet, Wallet destinationWallet) {
        this.transferId = transferId;
        this.transferDate = transferDate;
        this.amount = amount;
        this.originWallet = originWallet;
        this.destinationWallet = destinationWallet;
    }

    public Transfer(BigDecimal amount, Wallet originWallet, Wallet destinationWallet) {
        this.transferDate = LocalDateTime.now();
        this.amount = amount;
        this.originWallet = originWallet;
        this.destinationWallet = destinationWallet;
    }

    public Transfer() {
    }

    public UUID getTransferId() {
        return transferId;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Wallet getOriginWallet() {
        return originWallet;
    }

    public Wallet getDestinationWallet() {
        return destinationWallet;
    }
}
