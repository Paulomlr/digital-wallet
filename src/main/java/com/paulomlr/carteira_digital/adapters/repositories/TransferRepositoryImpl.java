package com.paulomlr.carteira_digital.adapters.repositories;

import com.paulomlr.carteira_digital.adapters.mappers.TransferMapper;
import com.paulomlr.carteira_digital.application.gateways.TransferRepository;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.TransferNotFound;
import com.paulomlr.carteira_digital.core.domain.Transfer;
import com.paulomlr.carteira_digital.infra.persistence.jpa.TransferRepositoryJpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TransferRepositoryImpl implements TransferRepository {

    private final TransferRepositoryJpa transferRepositoryJpa;
    private final TransferMapper transferMapper;

    public TransferRepositoryImpl(TransferRepositoryJpa transferRepositoryJpa, TransferMapper transferMapper) {
        this.transferRepositoryJpa = transferRepositoryJpa;
        this.transferMapper = transferMapper;
    }

    @Override
    public Transfer save(Transfer transfer) {
        var transferEntity = transferMapper.toEntity(transfer);
        var transferSaved = transferRepositoryJpa.save(transferEntity);
        return transferMapper.toDomain(transferSaved);
    }

    @Override
    public List<Transfer> findByWalletId(UUID walletId) {
        var transferEntity = transferRepositoryJpa.findByWalletId(walletId);

        return transferEntity.stream()
                .map(transferMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Transfer> findById(UUID transferId) {
        return transferRepositoryJpa.findById(transferId)
                .map(transferMapper::toDomain)
                .or(() -> {
                    throw new TransferNotFound("Transfer not found!");
                });
    }
}

