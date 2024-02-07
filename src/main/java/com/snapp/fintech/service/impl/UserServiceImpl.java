package com.snapp.fintech.service.impl;

import com.snapp.fintech.domain.UserEntity;
import com.snapp.fintech.repository.UserRepository;
import com.snapp.fintech.service.UserService;
import com.snapp.fintech.service.dto.UserDto;
import com.snapp.fintech.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return User.builder().username(user.getUsername()).password(user.getPassword()).build();
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return mapper.convertToDto(repository.save(mapper.convertToEntity(userDto)));
    }
}
