package com.project.fintechprac.repos;

import com.project.fintechprac.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    boolean existsTransactionByExternalId(String externalId);
}
