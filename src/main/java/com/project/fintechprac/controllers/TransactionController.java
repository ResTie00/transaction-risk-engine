package com.project.fintechprac.controllers;

import com.project.fintechprac.dtos.TransactionRequestDTO;
import com.project.fintechprac.dtos.TransactionResponseDTO;
import com.project.fintechprac.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> addTransaction(@RequestBody @Valid TransactionRequestDTO requestDTO) {
        TransactionResponseDTO transactionResponseDTO = transactionService.createTransaction(requestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transactionResponseDTO.id())
                .toUri();
        return ResponseEntity.created(location).body(transactionResponseDTO);
    }

    @GetMapping("/{id}")
    public TransactionResponseDTO getTransaction(@PathVariable Long id) {
        return transactionService.findTransactionById(id);
    }
}
