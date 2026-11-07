package com.project.fintechprac.dtos;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionResponseDTO(
        Long id,
        String externalId,
        String accountExternalId,
        BigDecimal amount,
        String currency,
        String merchantName,
        String mcc,
        String country,
        String deviceId,
        String ip,
        Instant occurredAt,
        Instant createdAt
) {}
