package com.paulomlr.carteira_digital.infra.config;

import com.paulomlr.carteira_digital.adapters.mappers.TransferMapper;
import com.paulomlr.carteira_digital.adapters.mappers.UserMapper;
import com.paulomlr.carteira_digital.adapters.mappers.WalletMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public WalletMapper walletMapper() {
        return new WalletMapper();
    }

    @Bean
    public UserMapper userMapper(WalletMapper walletMapper) {
        return new UserMapper(walletMapper);
    }

    @Bean
    public TransferMapper transferMapper(WalletMapper walletMapper) {
        return new TransferMapper(walletMapper);
    }
}
