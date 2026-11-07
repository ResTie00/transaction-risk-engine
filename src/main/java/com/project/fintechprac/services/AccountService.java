package com.project.fintechprac.services;

import com.project.fintechprac.dtos.AccountRequestDTO;
import com.project.fintechprac.dtos.AccountResponseDTO;
import com.project.fintechprac.exceptions.AccountNotFoundException;
import com.project.fintechprac.exceptions.DuplicateAccountException;
import com.project.fintechprac.mappers.AccountMapper;
import com.project.fintechprac.models.Account;
import com.project.fintechprac.repos.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional
    public AccountResponseDTO create(AccountRequestDTO accountRequestDTO) {
        if (accountRepository.existsByExternalId(accountRequestDTO.externalId())) {
            throw new DuplicateAccountException(accountRequestDTO.externalId());
        }
        Account savedAccount = accountRepository.save(accountMapper.toEntity(accountRequestDTO));
        return accountMapper.toDTO(savedAccount);
    }

    public AccountResponseDTO findByExternalId(String externalId) {
        Account account = accountRepository.findByExternalId(externalId)
                .orElseThrow(() -> new AccountNotFoundException("Account with externalId " + externalId + " not found."));
        return accountMapper.toDTO(account);
    }

    public AccountResponseDTO findById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + id + " not found."));
        return accountMapper.toDTO(account);
    }

}
