package com.snapp.fintech.config.openapi;

import com.snapp.fintech.config.constant.AppConstants;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = AppConstants.BEARER_AUTHORIZATION,
        type = SecuritySchemeType.HTTP,
        bearerFormat = AppConstants.JWT,
        scheme = AppConstants.BEARER
)
public class OpenAPIConfiguration {
}