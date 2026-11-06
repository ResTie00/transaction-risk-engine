package com.project.fintechprac.dtos;

import java.time.Instant;

public record AccountResponseDTO (
    Long id,
    String externalId,
    String homeCountry,
    Instant createdAt
){}
