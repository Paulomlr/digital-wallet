package com.paulomlr.carteira_digital.infra.persistence.jpa;

import com.paulomlr.carteira_digital.infra.persistence.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TransferRepositoryJpa extends JpaRepository<TransferEntity, UUID> {

    @Query("SELECT t FROM TransferEntity t WHERE t.originWallet.walletId = :walletId OR t.destinationWallet.walletId = :walletId")
    List<TransferEntity> findByWalletId(@Param("walletId") UUID walletId);
}