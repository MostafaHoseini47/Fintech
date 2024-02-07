package com.snapp.fintech.service.mapper;

import com.snapp.fintech.domain.ItemsEntity;
import com.snapp.fintech.service.dto.ItemsDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemsMapper {

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(source = "user", target = "user.id")
    @Mapping(source = "category", target = "category.id")
    ItemsEntity convertToEntity(ItemsDto dto);

    @InheritInverseConfiguration
    ItemsDto convertToDto(ItemsEntity entity);

}
