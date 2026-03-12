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

        if (authentication != null || !authentication.isAuthenticated()){

            return Optional.empty();

        }

        User user = (User) authentication.getPrincipal();

        return Optional.of(user.getId());

    }

}
