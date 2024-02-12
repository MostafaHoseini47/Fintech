package com.snapp.fintech.config.security.service;

import com.snapp.fintech.config.security.service.dto.request.SignUpRequest;
import com.snapp.fintech.config.security.service.dto.request.SigninRequest;
import com.snapp.fintech.config.security.service.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

}
