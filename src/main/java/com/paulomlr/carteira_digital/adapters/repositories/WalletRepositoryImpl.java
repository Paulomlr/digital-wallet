package com.paulomlr.carteira_digital.adapters.repositories;

import com.paulomlr.carteira_digital.adapters.mappers.WalletMapper;
import com.paulomlr.carteira_digital.application.gateways.WalletRepository;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.WalletNotFoundException;
import com.paulomlr.carteira_digital.core.domain.Wallet;
import com.paulomlr.carteira_digital.core.ports.WalletCodeUtils;
import com.paulomlr.carteira_digital.infra.persistence.jpa.WalletRepositoryJpa;

import java.util.Optional;
import java.util.UUID;

public class WalletRepositoryImpl implements WalletRepository {

    private final WalletRepositoryJpa walletRepositoryJpa;
    private final WalletMapper walletMapper;

    public WalletRepositoryImpl(WalletRepositoryJpa walletRepositoryJpa, WalletMapper walletMapper) {
        this.walletRepositoryJpa = walletRepositoryJpa;
        this.walletMapper = walletMapper;
    }

    @Override
    public Wallet save(Wallet wallet) {
        var walletEntity = walletMapper.toEntity(wallet);
        var walletSaved = walletRepositoryJpa.save(walletEntity);
        return walletMapper.toDomain(walletSaved);
    }

    @Override
    public Optional<Wallet> findById(UUID walletId) {
        return walletRepositoryJpa.findById(walletId)
                .map(walletMapper::toDomain)
                .or(() -> {
                    throw new WalletNotFoundException("WalletId does not exist!");
                });
    }

    @Override
    public Optional<Wallet> findByWalletCode(String walletCode) {
        return walletRepositoryJpa.findByWalletCode(WalletCodeUtils.removeFormatting(walletCode))
                .map(walletMapper::toDomain)
                .or(() -> {
                    throw new WalletNotFoundException("WalletCode does not exist!");
                });
    }

    @Override
    public boolean existsByWalletCode(String walletCode) {
        return walletRepositoryJpa.existsByWalletCode(walletCode);
    }
}
