package com.project.fintechprac.controllers;

import com.project.fintechprac.dtos.AccountRequestDTO;
import com.project.fintechprac.dtos.AccountResponseDTO;
import com.project.fintechprac.services.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody @Valid AccountRequestDTO accountRequestDTO) {
        AccountResponseDTO accountResponseDTO = accountService.create(accountRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(accountResponseDTO.id())
                .toUri();
        return ResponseEntity.created(location).body(accountResponseDTO);
    }

    @GetMapping("/by-externalid/{externalId}")
    public AccountResponseDTO getAccountByExternalId(@PathVariable String externalId) {
        return accountService.findByExternalId(externalId);
    }
    @GetMapping("/{id}")
    public AccountResponseDTO getAccountById(@PathVariable Long id) {
        return accountService.findById(id);
    }
}
