package com.snapp.fintech.config.security.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private BigDecimal stock;
}
