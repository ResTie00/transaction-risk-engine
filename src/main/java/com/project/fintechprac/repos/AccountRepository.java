package com.project.fintechprac.repos;

import com.project.fintechprac.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByExternalId(String externalId);
    boolean existsByExternalId(String externalId);
}
