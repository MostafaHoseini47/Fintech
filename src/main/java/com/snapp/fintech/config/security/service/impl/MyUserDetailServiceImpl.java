package com.snapp.fintech.config.security.service.impl;

import com.snapp.fintech.config.security.service.MyUserDetailService;
import com.snapp.fintech.domain.UserEntity;
import com.snapp.fintech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailServiceImpl implements MyUserDetailService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return User.builder().username(user.getUsername()).password(user.getPassword()).build();
    }
}
