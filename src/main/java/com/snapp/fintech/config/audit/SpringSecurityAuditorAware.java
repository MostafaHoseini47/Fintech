package com.snapp.fintech.config.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal;
        if (authentication.isAuthenticated()) {
            principal = authentication.getPrincipal();
            if (principal instanceof String) {
                return Optional.of(principal.toString());
            } else if (principal instanceof User) {
                return Optional.of(((User) principal).getUsername());
            }
        }

        return Optional.empty();
    }
}