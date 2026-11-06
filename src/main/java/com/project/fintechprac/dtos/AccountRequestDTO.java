package com.project.fintechprac.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



public record AccountRequestDTO (
    @Size(min = 1, max = 64, message = "External ID must be between 1 and 64 characters")
    @NotBlank(message = "External ID is required")
    String externalId,

    @Pattern(regexp = "^[A-Z]{2}$", message = "Home country must be a valid ISO 3166-1 alpha-2 country code")
    @NotBlank(message = "Home country is required")
    String homeCountry
){}
