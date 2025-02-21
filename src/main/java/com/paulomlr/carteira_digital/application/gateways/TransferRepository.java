package com.paulomlr.carteira_digital.application.gateways;

import com.paulomlr.carteira_digital.core.domain.Transfer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransferRepository {

    Transfer save(Transfer transfer);
    List<Transfer> findByWalletId(UUID walletId);
    Optional<Transfer> findById(UUID transferId);
}