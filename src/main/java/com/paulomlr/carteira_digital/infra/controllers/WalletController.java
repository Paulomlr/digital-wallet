package com.paulomlr.carteira_digital.infra.controllers;

import com.paulomlr.carteira_digital.adapters.dtos.wallet.WalletAddBalanceRequestDTO;
import com.paulomlr.carteira_digital.adapters.dtos.wallet.WalletBalanceResponseDTO;
import com.paulomlr.carteira_digital.infra.services.WalletService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/wallets")
@AllArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/{userId}/balance")
    public ResponseEntity<WalletBalanceResponseDTO> getBalance(@PathVariable UUID userId) {
        WalletBalanceResponseDTO response = walletService.getBalance(userId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/balance")
    public ResponseEntity<Void> addBalance(@PathVariable UUID userId, @RequestBody @Valid WalletAddBalanceRequestDTO walletRequest) {
        walletService.addBalance(userId, walletRequest.value());
        return ResponseEntity.ok().build();
    }
}
