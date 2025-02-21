package com.paulomlr.carteira_digital.application.usecaseimpl;

import com.paulomlr.carteira_digital.application.gateways.TransferRepository;
import com.paulomlr.carteira_digital.application.gateways.UserRepository;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.UserNotFoundException;
import com.paulomlr.carteira_digital.core.domain.Transfer;
import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.core.domain.Wallet;
import com.paulomlr.carteira_digital.core.usecase.ListTransfersUseCase;

import java.util.List;
import java.util.UUID;

public class ListTransfersUseCaseImpl implements ListTransfersUseCase {

    private final TransferRepository transferRepository;
    private final UserRepository userRepository;

    public ListTransfersUseCaseImpl(TransferRepository transferRepository, UserRepository userRepository) {
        this.transferRepository = transferRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Transfer> list(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        return transferRepository.findByWalletId(user.getWallet().getWalletId());
    }
}
