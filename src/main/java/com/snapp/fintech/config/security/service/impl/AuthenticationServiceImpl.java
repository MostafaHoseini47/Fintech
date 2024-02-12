package com.snapp.fintech.config.security.service.impl;

import com.snapp.fintech.config.security.service.AuthenticationService;
import com.snapp.fintech.config.security.service.JwtService;
import com.snapp.fintech.config.security.service.dto.request.SignUpRequest;
import com.snapp.fintech.config.security.service.dto.request.SigninRequest;
import com.snapp.fintech.config.security.service.dto.response.JwtAuthenticationResponse;
import com.snapp.fintech.domain.UserEntity;
import com.snapp.fintech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        UserEntity user = UserEntity.builder()
                .firstName(request.getFirstName()).lastName(request.getLastName()).username(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword())).stock(request.getStock()).build();
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        UserEntity user = userRepository.findByUsername(request.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + request.getUserName()));
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
