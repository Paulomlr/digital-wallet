package com.paulomlr.carteira_digital.adapters.repositories;

import com.paulomlr.carteira_digital.adapters.mappers.UserMapper;
import com.paulomlr.carteira_digital.application.gateways.UserRepository;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.UserNotFoundException;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.WalletNotFoundException;
import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.infra.persistence.jpa.UserRepositoryJpa;

import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryJpa userRepositoryJpa;
    private final UserMapper userMapper;

    public UserRepositoryImpl(UserRepositoryJpa userRepositoryJpa, UserMapper userMapper) {
        this.userRepositoryJpa = userRepositoryJpa;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        var userEntity = userMapper.toEntity(user);
        var userSaved = userRepositoryJpa.save(userEntity);
        return userMapper.toDomain(userSaved);
    }

    @Override
    public Optional<User> findByEmail(String userEmail) {
        return userRepositoryJpa.findByEmail(userEmail)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return userRepositoryJpa.findById(userId)
                .map(userMapper::toDomain)
                .or(() -> {
                    throw new UserNotFoundException("User not found!");
                });
    }

    @Override
    public Optional<User> findByWallet_WalletId(UUID walletId) {
        return userRepositoryJpa.findByWallet_WalletId(walletId)
                .map(userMapper::toDomain)
                .or(() -> {
                    throw new WalletNotFoundException("Wallet nof found!");
                });
    }
}
