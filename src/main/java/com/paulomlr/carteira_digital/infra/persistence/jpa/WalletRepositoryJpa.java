package com.paulomlr.carteira_digital.infra.persistence.jpa;

import com.paulomlr.carteira_digital.infra.persistence.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepositoryJpa extends JpaRepository<WalletEntity, UUID> {

    Optional<WalletEntity> findByWalletCode(String walletCode);
    boolean existsByWalletCode(String walletCode);
}
