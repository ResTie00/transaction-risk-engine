package com.project.fintechprac.mappers;

import com.project.fintechprac.dtos.TransactionRequestDTO;
import com.project.fintechprac.dtos.TransactionResponseDTO;
import com.project.fintechprac.models.Account;
import com.project.fintechprac.models.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TransactionMapper {
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Transaction toEntity(TransactionRequestDTO dto);

    @Mapping(source = "account.externalId", target = "accountExternalId")
    TransactionResponseDTO toDTO(Transaction transaction);
}
