package com.paulomlr.carteira_digital.adapters.mappers;

import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.infra.persistence.entity.UserEntity;

public class UserMapper {

    private final WalletMapper walletMapper;

    public UserMapper(WalletMapper walletMapper) {
        this.walletMapper = walletMapper;
    }

    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                walletMapper.toEntity(user.getWallet())
        );
    }

    public User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getUserId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                walletMapper.toDomain(userEntity.getWallet())
        );
    }
}
