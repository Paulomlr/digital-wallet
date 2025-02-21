package com.paulomlr.carteira_digital.core.usecase;

import com.paulomlr.carteira_digital.core.domain.Transfer;

import java.util.List;
import java.util.UUID;

public interface ListTransfersUseCase {

    List<Transfer> list(UUID userId);
}
