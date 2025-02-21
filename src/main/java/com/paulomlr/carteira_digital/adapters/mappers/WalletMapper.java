package com.paulomlr.carteira_digital.adapters.mappers;

import com.paulomlr.carteira_digital.core.domain.Wallet;
import com.paulomlr.carteira_digital.infra.persistence.entity.WalletEntity;

public class WalletMapper {

    public WalletEntity toEntity(Wallet wallet) {
        return new WalletEntity(
                wallet.getWalletId(),
                wallet.getWalletCode(),
                wallet.getBalance()
        );
    }

    public Wallet toDomain(WalletEntity walletEntity) {
        return new Wallet(
                walletEntity.getWalletId(),
                walletEntity.getWalletCode(),
                walletEntity.getBalance()
        );
    }
}
