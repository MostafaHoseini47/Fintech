package com.snapp.fintech.service.impl;

import com.snapp.fintech.config.constant.AppConstants;
import com.snapp.fintech.domain.UserEntity;
import com.snapp.fintech.exception.UnauthorizedException;
import com.snapp.fintech.exception.UserHaveNotException;
import com.snapp.fintech.repository.UserRepository;
import com.snapp.fintech.service.UserService;
import com.snapp.fintech.service.dto.ChangePasswordDto;
import com.snapp.fintech.service.dto.UserDto;
import com.snapp.fintech.service.mapper.UserMapper;
import com.snapp.fintech.web.rest.model.ModelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper mapper;


    @Override
    @Transactional
    public UserDto saveUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return mapper.convertToDto(repository.save(mapper.convertToEntity(userDto)));
    }

    @Override
    @Transactional
    public ModelResponse deactivateUser(String userName) {
        UserEntity user = repository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(AppConstants.USER_NOT_FOUND + userName));
        user.setIsActivate(Boolean.TRUE);
        repository.save(user);
        return ModelResponse.builder().message("User with username: " + userName + "successfully deactivated ")
                .code(200).build();
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) {
        UserEntity user = repository
                .findById(userDto.getId())
                .orElseThrow(() -> new UsernameNotFoundException(AppConstants.USER_NOT_FOUND + userDto.getId()));
        if (!Objects.equals(userDto.getFirstName(), user.getFirstName())) {
            user.setFirstName(userDto.getFirstName());
        }
        if (!Objects.equals(userDto.getLastName(), user.getLastName())) {
            user.setLastName(userDto.getLastName());
        }
        if (!Objects.equals(userDto.getUsername(), user.getUsername())) {
            user.setUsername(userDto.getUsername());
        }
        if (!Objects.equals(userDto.getStock(), user.getStock())) {
            user.setStock(userDto.getStock());
        }
        return mapper.convertToDto(repository.save(user));
    }

    @Override
    @Transactional
    public ModelResponse changePassword(ChangePasswordDto changePasswordDto) {
        changePasswordDto.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        UserEntity user = repository.findByUsername(changePasswordDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(AppConstants.USER_NOT_FOUND + changePasswordDto.getUsername()));
        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
            throw new UnauthorizedException("Incorrect old password");
        }
        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        repository.save(user);
        return ModelResponse.builder().message("Password changed successfully").code(200).build();
    }

    @Override
    public UserEntity getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(AppConstants.USER_NOT_FOUND + username));
    }

    @Override
    public UserEntity getCurrentUserAndCalculateStock(BigDecimal itemPrice) {
        UserEntity currentUser = getCurrentUser();
        if (itemPrice.compareTo(currentUser.getStock()) > 0) {
            throw new UserHaveNotException(AppConstants.CURRENT_USER_DOESNT_HAS_MONEY);
        }
        currentUser.setStock(currentUser.getStock().subtract(itemPrice));
        return repository.save(currentUser);
    }
}
