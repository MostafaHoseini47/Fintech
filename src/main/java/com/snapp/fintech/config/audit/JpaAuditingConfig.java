package com.snapp.fintech.config.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            return Optional.ofNullable(userName);
        };
    }

    @Bean
    public AuditorAware<String> springSecurityAuditorAware() {
        return new SpringSecurityAuditorAware();
    }
}
