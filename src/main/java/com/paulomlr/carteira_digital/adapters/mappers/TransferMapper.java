package com.paulomlr.carteira_digital.adapters.mappers;

import com.paulomlr.carteira_digital.core.domain.Transfer;
import com.paulomlr.carteira_digital.infra.persistence.entity.TransferEntity;

public class TransferMapper {

    private final WalletMapper walletMapper;

    public TransferMapper(WalletMapper walletMapper) {
        this.walletMapper = walletMapper;
    }

    public TransferEntity toEntity(Transfer transfer) {
        return new TransferEntity(
                transfer.getTransferId(),
                transfer.getTransferDate(),
                transfer.getAmount(),
                walletMapper.toEntity(transfer.getOriginWallet()),
                walletMapper.toEntity(transfer.getDestinationWallet())
        );
    }

    public Transfer toDomain(TransferEntity transferEntity) {
        return new Transfer(
                    transferEntity.getTransferId(),
                    transferEntity.getTransferDate(),
                    transferEntity.getAmount(),
                    walletMapper.toDomain(transferEntity.getOriginWallet()),
                    walletMapper.toDomain(transferEntity.getDestinationWallet())
        );
    }
}
