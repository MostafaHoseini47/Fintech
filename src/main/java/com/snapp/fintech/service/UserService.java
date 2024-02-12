package com.snapp.fintech.service;

import com.snapp.fintech.domain.UserEntity;
import com.snapp.fintech.service.dto.ChangePasswordDto;
import com.snapp.fintech.service.dto.UserDto;
import com.snapp.fintech.web.rest.model.ModelResponse;

import java.math.BigDecimal;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    ModelResponse deactivateUser(String userName);

    UserDto updateUser(UserDto userDto);

    ModelResponse changePassword(ChangePasswordDto changePasswordDto);

    UserEntity getCurrentUser();

    UserEntity getCurrentUserAndCalculateStock(BigDecimal itemPrice);

}
