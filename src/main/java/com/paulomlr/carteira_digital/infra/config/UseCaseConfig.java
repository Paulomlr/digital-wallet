package com.paulomlr.carteira_digital.infra.config;

import com.paulomlr.carteira_digital.application.gateways.TransferRepository;
import com.paulomlr.carteira_digital.application.gateways.UserRepository;
import com.paulomlr.carteira_digital.application.gateways.WalletRepository;
import com.paulomlr.carteira_digital.application.usecaseimpl.*;
import com.paulomlr.carteira_digital.core.ports.PasswordEncoder;
import com.paulomlr.carteira_digital.core.ports.TokenService;
import com.paulomlr.carteira_digital.core.usecase.CreateWalletUseCase;
import com.paulomlr.carteira_digital.core.usecase.ExecuteTransferUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public AddBalanceUseCaseImpl addBalanceUseCase(UserRepository userRepository, WalletRepository walletRepository) {
        return new AddBalanceUseCaseImpl(userRepository, walletRepository);
    }

    @Bean
    public CheckBalanceUseCaseImpl checkBalanceUseCase(UserRepository userRepository) {
        return new CheckBalanceUseCaseImpl(userRepository);
    }

    @Bean
    public CreateTransferUseCaseImpl createTransferUseCase(TransferRepository transferRepository, WalletRepository walletRepository, ExecuteTransferUseCase executeTransferUseCase, UserRepository userRepository) {
        return new CreateTransferUseCaseImpl(transferRepository, walletRepository, executeTransferUseCase, userRepository);
    }

    @Bean
    public ExecuteTransferUseCaseImpl executeTransferUseCase(WalletRepository walletRepository) {
        return new ExecuteTransferUseCaseImpl(walletRepository);
    }

    @Bean
    public CreateWalletUseCaseImpl createWalletUseCase (WalletRepository walletRepository) {
        return new CreateWalletUseCaseImpl(walletRepository);
    }

    @Bean
    public CreateUserUseCaseImpl createUserUseCase(UserRepository userRepository, CreateWalletUseCase createWalletUseCase, PasswordEncoder passwordEncoder) {
        return new CreateUserUseCaseImpl(userRepository, createWalletUseCase, passwordEncoder);
    }

    @Bean
    public ListTransfersUseCaseImpl listTransfersUseCase(TransferRepository transferRepository, UserRepository userRepository) {
        return new ListTransfersUseCaseImpl(transferRepository, userRepository);
    }

    @Bean
    public LoginUseCaseImpl loginUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        return new LoginUseCaseImpl(userRepository, passwordEncoder, tokenService);
    }
}
