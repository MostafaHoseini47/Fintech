package com.snapp.fintech.service.mapper;

import com.snapp.fintech.domain.CategoryEntity;
import com.snapp.fintech.service.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    CategoryEntity convertToEntity(CategoryDto dto);

    CategoryDto convertToDto(CategoryEntity entity);

}
