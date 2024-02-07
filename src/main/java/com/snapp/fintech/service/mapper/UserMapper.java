package com.snapp.fintech.service.mapper;

import com.snapp.fintech.domain.UserEntity;
import com.snapp.fintech.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserEntity convertToEntity(UserDto dto);

    UserDto convertToDto(UserEntity entity);
}
