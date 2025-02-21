package com.paulomlr.carteira_digital.infra.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_transfer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transfer_id")
    private UUID transferId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT-3")
    private LocalDateTime transferDate;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_wallet", referencedColumnName = "wallet_id")
    private WalletEntity originWallet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_wallet", referencedColumnName = "wallet_id")
    private WalletEntity destinationWallet;

    public TransferEntity(LocalDateTime transferDate, BigDecimal amount, WalletEntity originWallet, WalletEntity destinationWallet) {
        this.transferDate = transferDate;
        this.amount = amount;
        this.originWallet = originWallet;
        this.destinationWallet = destinationWallet;
    }
}
