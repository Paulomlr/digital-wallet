package com.paulomlr.carteira_digital.infra.config;

import com.paulomlr.carteira_digital.adapters.mappers.TransferMapper;
import com.paulomlr.carteira_digital.adapters.mappers.UserMapper;
import com.paulomlr.carteira_digital.adapters.mappers.WalletMapper;
import com.paulomlr.carteira_digital.adapters.repositories.TransferRepositoryImpl;
import com.paulomlr.carteira_digital.adapters.repositories.UserRepositoryImpl;
import com.paulomlr.carteira_digital.adapters.repositories.WalletRepositoryImpl;
import com.paulomlr.carteira_digital.infra.persistence.jpa.TransferRepositoryJpa;
import com.paulomlr.carteira_digital.infra.persistence.jpa.UserRepositoryJpa;
import com.paulomlr.carteira_digital.infra.persistence.jpa.WalletRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public TransferRepositoryImpl transferRepository(TransferRepositoryJpa transferRepositoryJpa, TransferMapper transferMapper) {
        return new TransferRepositoryImpl(transferRepositoryJpa, transferMapper);
    }

    @Bean
    public UserRepositoryImpl userRepository(UserRepositoryJpa userRepositoryJpa, UserMapper userMapper) {
        return new UserRepositoryImpl(userRepositoryJpa, userMapper);
    }

    @Bean
    public WalletRepositoryImpl walletRepository(WalletRepositoryJpa walletRepositoryJpa, WalletMapper walletMapper) {
        return new WalletRepositoryImpl(walletRepositoryJpa, walletMapper);
    }
}
