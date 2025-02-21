package com.paulomlr.carteira_digital.infra.persistence.jpa;

import com.paulomlr.carteira_digital.infra.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String userEmail);
    Optional<UserEntity> findByWallet_WalletId(UUID walletId);
}
