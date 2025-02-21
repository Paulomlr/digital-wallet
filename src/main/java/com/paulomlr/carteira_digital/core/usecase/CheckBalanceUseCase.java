package com.paulomlr.carteira_digital.core.usecase;

import java.math.BigDecimal;
import java.util.UUID;

public interface CheckBalanceUseCase {

    BigDecimal check(UUID userId);
}
