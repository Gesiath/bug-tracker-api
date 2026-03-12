package com.gesiath.bugtrackerapi.config;

import com.gesiath.bugtrackerapi.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AuditorAwareImpl implements AuditorAware<UUID> {

    @Override
    public Optional<UUID> getCurrentAuditor() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.of(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof User) {
            User user = (User) principal;
            return Optional.of(user.getId());
        }

        return Optional.of(UUID.fromString("00000000-0000-0000-0000-000000000001"));
    }

}
