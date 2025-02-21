package com.paulomlr.carteira_digital.infra.config;

import com.paulomlr.carteira_digital.application.usecaseimpl.CreateTransferUseCaseImpl;
import com.paulomlr.carteira_digital.infra.config.security.PasswordEncodeImpl;
import com.paulomlr.carteira_digital.infra.persistence.entity.UserEntity;
import com.paulomlr.carteira_digital.infra.persistence.entity.WalletEntity;
import com.paulomlr.carteira_digital.infra.persistence.jpa.UserRepositoryJpa;
import com.paulomlr.carteira_digital.infra.persistence.jpa.WalletRepositoryJpa;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

    private final WalletRepositoryJpa walletRepositoryJpa;
    private final UserRepositoryJpa userRepositoryJpa;
    private final PasswordEncodeImpl passwordEncoder;
    private final CreateTransferUseCaseImpl createTransferUseCase;

    public DataInitializer(WalletRepositoryJpa walletRepositoryJpa,
                           UserRepositoryJpa userRepositoryJpa,
                           PasswordEncodeImpl passwordEncoder,
                           CreateTransferUseCaseImpl createTransferUseCase) {
        this.walletRepositoryJpa = walletRepositoryJpa;
        this.userRepositoryJpa = userRepositoryJpa;
        this.passwordEncoder = passwordEncoder;
        this.createTransferUseCase = createTransferUseCase;
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            if (walletRepositoryJpa.count() == 0 && userRepositoryJpa.count() == 0) {

                List<WalletEntity> wallets = Arrays.asList(
                        new WalletEntity("8780686119879626", BigDecimal.valueOf(1450)),
                        new WalletEntity("3546080964015732", BigDecimal.valueOf(10000)),
                        new WalletEntity("6087032330016361", BigDecimal.valueOf(1000))
                );

                List<UserEntity> users = userRepositoryJpa.saveAll(Arrays.asList(
                        new UserEntity("Ricardo Mendes Costa", "ricardo.costa@gmail.com", passwordEncoder.encode("Ricardo123@"), wallets.get(0)),
                        new UserEntity("Luciana Ferreira Silva", "luciana.ferreira@outlook.com", passwordEncoder.encode("LuciAna_2025!"), wallets.get(1)),
                        new UserEntity("Carlos Alberto Oliveira", "carlos.alberto@yahoo.com", passwordEncoder.encode("Alberto#Carlo8"), wallets.get(2))
                ));

                createTransferUseCase.create(
                        BigDecimal.valueOf(1200),
                        users.get(1).getUserId(),
                        wallets.get(2).getWalletCode()
                );

                createTransferUseCase.create(
                        BigDecimal.valueOf(500),
                        users.get(0).getUserId(),
                        wallets.get(2).getWalletCode()
                );
            }
        };
    }
}
