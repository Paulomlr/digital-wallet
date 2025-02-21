package com.paulomlr.carteira_digital.infra.controllers;

import com.paulomlr.carteira_digital.adapters.dtos.transfer.TransferRequestDTO;
import com.paulomlr.carteira_digital.adapters.dtos.transfer.TransferResponseDTO;
import com.paulomlr.carteira_digital.infra.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Void> createTransfer(@PathVariable UUID userId, @RequestBody @Valid TransferRequestDTO transferRequest) {
        TransferResponseDTO transferResponse = transferService.createTransfer(userId, transferRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transferResponse.transferId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TransferResponseDTO>> listTransfers(@PathVariable UUID userId) {
        List<TransferResponseDTO> listTransfers = transferService.listTransfers(userId);

        return ResponseEntity.ok(listTransfers);
    }
}
