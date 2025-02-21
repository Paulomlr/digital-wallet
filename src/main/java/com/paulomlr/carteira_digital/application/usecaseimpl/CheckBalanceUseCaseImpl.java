package com.paulomlr.carteira_digital.application.usecaseimpl;

import com.paulomlr.carteira_digital.application.gateways.UserRepository;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.UserNotFoundException;
import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.core.usecase.CheckBalanceUseCase;

import java.math.BigDecimal;
import java.util.UUID;

public class CheckBalanceUseCaseImpl implements CheckBalanceUseCase {

    private final UserRepository userRepository;

    public CheckBalanceUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public BigDecimal check(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        return user.getWallet().getBalance();
    }
}
