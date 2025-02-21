package com.paulomlr.carteira_digital.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_wallet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "wallet_id")
    private UUID walletId;

    private String walletCode;

    private BigDecimal balance;

    @OneToOne(mappedBy = "wallet")
    private UserEntity user;

    public WalletEntity(UUID walletId, String walletCode, BigDecimal balance) {
        this.walletId = walletId;
        this.walletCode = walletCode;
        this.balance = balance;
    }

    public WalletEntity(String walletCode, BigDecimal balance) {
        this.walletCode = walletCode;
        this.balance = balance;
    }
}
