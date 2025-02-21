package com.paulomlr.carteira_digital.application.gateways;

import com.paulomlr.carteira_digital.core.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);
    Optional<User> findByEmail(String userEmail);
    Optional<User> findById(UUID userId);
    Optional<User> findByWallet_WalletId(UUID walletId);
}
