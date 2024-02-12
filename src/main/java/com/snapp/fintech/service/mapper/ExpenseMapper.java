package com.snapp.fintech.service.mapper;

import com.snapp.fintech.domain.ExpenseEntity;
import com.snapp.fintech.service.dto.ExpenseDto;
import com.snapp.fintech.service.dto.ExpenseResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExpenseMapper {

    @Mapping(target = "expenseDate", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(source = "category", target = "category.id")
    ExpenseEntity convertToEntity(ExpenseDto dto);

    @Mapping(source = "category.id", target = "category")
    ExpenseResponseDto convertToDto(ExpenseEntity entity);

}
