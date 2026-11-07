package com.project.fintechprac.services;

import com.project.fintechprac.dtos.TransactionRequestDTO;
import com.project.fintechprac.dtos.TransactionResponseDTO;
import com.project.fintechprac.exceptions.AccountNotFoundException;
import com.project.fintechprac.exceptions.DuplicateTransactionException;
import com.project.fintechprac.exceptions.TransactionNotFoundException;
import com.project.fintechprac.mappers.TransactionMapper;
import com.project.fintechprac.models.Account;
import com.project.fintechprac.models.Transaction;
import com.project.fintechprac.repos.AccountRepository;
import com.project.fintechprac.repos.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;
    @Transactional
    public TransactionResponseDTO createTransaction(TransactionRequestDTO dto) {
        Account account = accountRepository.findByExternalId(dto.accountExternalId())
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account with externalId " + dto.accountExternalId() + " not found"));

        if (transactionRepository.existsTransactionByExternalId(dto.externalId())) {
            throw new DuplicateTransactionException(dto.externalId());
        }

        Transaction transaction = transactionMapper.toEntity(dto).toBuilder()
                .account(account)
                .build();
        Transaction saved = transactionRepository.save(transaction);
        return transactionMapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public TransactionResponseDTO findTransactionById(Long id){
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() ->new TransactionNotFoundException("Transaction with id : " + id + "not found"));
        return transactionMapper.toDTO(transaction);
    }
}
