package com.paulomlr.carteira_digital.application.usecaseimpl;

import com.paulomlr.carteira_digital.application.gateways.TransferRepository;
import com.paulomlr.carteira_digital.application.gateways.UserRepository;
import com.paulomlr.carteira_digital.application.gateways.WalletRepository;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.SameWalletTransferException;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.UserNotFoundException;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.WalletNotFoundException;
import com.paulomlr.carteira_digital.core.domain.Transfer;
import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.core.domain.Wallet;
import com.paulomlr.carteira_digital.core.usecase.CreateTransferUseCase;
import com.paulomlr.carteira_digital.core.usecase.ExecuteTransferUseCase;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateTransferUseCaseImpl implements CreateTransferUseCase {

    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;
    private final ExecuteTransferUseCase executeTransferUseCase;
    private final UserRepository userRepository;

    public CreateTransferUseCaseImpl(TransferRepository transferRepository, WalletRepository walletRepository,
                                     ExecuteTransferUseCase executeTransferUseCase, UserRepository userRepository) {
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
        this.executeTransferUseCase = executeTransferUseCase;
        this.userRepository = userRepository;
    }

    @Override
    public Transfer create(BigDecimal value, UUID userID, String destinationWalletCode) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        Wallet originWallet = user.getWallet();

        Wallet destinationWallet = walletRepository.findByWalletCode(destinationWalletCode)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found!"));

        if(originWallet.getWalletId() == destinationWallet.getWalletId()) {
            throw new SameWalletTransferException("Transfer to the same wallet is not allowed!");
        }

        executeTransferUseCase.process(value, originWallet, destinationWallet);

        Transfer transfer = new Transfer(value, originWallet, destinationWallet);

        return transferRepository.save(transfer);
    }
}
