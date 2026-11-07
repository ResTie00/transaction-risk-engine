package com.project.fintechprac.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionRequestDTO (
   @NotBlank @Size(max = 64, message = "External id limited to 64 characters")
   String externalId,
   @NotBlank @Size(max = 64, message = "AccExtId limited to 64 characters")
   String accountExternalId,
   @NotNull @Positive @Digits(integer = 15, fraction = 4)
   BigDecimal amount,
   @NotBlank @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a 3-letter uppercase ISO code, e.g. USD")
   String currency,
   @NotBlank @Size(max = 255)
   String merchantName,
   @Pattern(regexp = "^\\d{4}$")
   String mcc,
   @NotBlank @Pattern(regexp = "^[A-Z]{2}$")
   String country,
   @Size(max = 128)
   String deviceId,
   @Size(max = 45)
   String ip,
   @NotNull @PastOrPresent
   Instant occurredAt
){}
