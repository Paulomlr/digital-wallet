package com.paulomlr.carteira_digital.infra.services;

import com.paulomlr.carteira_digital.adapters.dtos.transfer.TransferRequestDTO;
import com.paulomlr.carteira_digital.adapters.dtos.transfer.TransferResponseDTO;
import com.paulomlr.carteira_digital.application.gateways.UserRepository;
import com.paulomlr.carteira_digital.application.usecaseimpl.exceptions.UserNotFoundException;
import com.paulomlr.carteira_digital.core.domain.Transfer;
import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.core.usecase.CreateTransferUseCase;
import com.paulomlr.carteira_digital.core.usecase.ListTransfersUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransferService  {

    private final CreateTransferUseCase createTransferUseCase;
    private final ListTransfersUseCase listTransfersUseCase;
    private final UserRepository userRepository;

    public TransferService(CreateTransferUseCase createTransferUseCase, ListTransfersUseCase listTransfersUseCase, UserRepository userRepository) {
        this.createTransferUseCase = createTransferUseCase;
        this.listTransfersUseCase = listTransfersUseCase;
        this.userRepository = userRepository;
    }


    public TransferResponseDTO createTransfer(UUID userId, TransferRequestDTO transferRequest) {
        Transfer transfer = createTransferUseCase.create(transferRequest.value(), userId, transferRequest.destinationWalletCode());
        return buildTransferResponse(transfer);
    }

    public List<TransferResponseDTO> listTransfers (UUID userId) {
        return listTransfersUseCase.list(userId)
                .stream()
                .map(this::buildTransferResponse)
                .toList();
    }

    private TransferResponseDTO buildTransferResponse(Transfer transfer) {
        User userOrigin = userRepository.findByWallet_WalletId(transfer.getOriginWallet().getWalletId())
                .orElseThrow(() -> new UserNotFoundException("Origin user not found!"));
        User userDestination = userRepository.findByWallet_WalletId(transfer.getDestinationWallet().getWalletId())
                .orElseThrow(() -> new UserNotFoundException("Destination user not found!"));

        return new TransferResponseDTO(transfer, userOrigin, userDestination);
    }
}