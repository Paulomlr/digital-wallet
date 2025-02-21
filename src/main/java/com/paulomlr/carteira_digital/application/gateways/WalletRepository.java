package com.paulomlr.carteira_digital.application.gateways;

import com.paulomlr.carteira_digital.core.domain.Wallet;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository {

    Wallet save(Wallet wallet);
    Optional<Wallet> findById(UUID walletId);
    Optional<Wallet> findByWalletCode(String walletCode);
    boolean existsByWalletCode(String walletCode);
}
