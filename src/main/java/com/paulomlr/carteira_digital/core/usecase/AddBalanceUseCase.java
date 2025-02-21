package com.paulomlr.carteira_digital.core.usecase;

import java.math.BigDecimal;
import java.util.UUID;

public interface AddBalanceUseCase {

    void add(UUID userId, BigDecimal value);
}
