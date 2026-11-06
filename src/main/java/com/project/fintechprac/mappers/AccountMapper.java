package com.project.fintechprac.mappers;

import com.project.fintechprac.dtos.AccountRequestDTO;
import com.project.fintechprac.dtos.AccountResponseDTO;
import com.project.fintechprac.models.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.ERROR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AccountMapper {
    @Mapping(target = "createdAt",  ignore = true)
    Account toEntity(AccountRequestDTO requestDTO);

    AccountResponseDTO toDTO(Account account);
}
