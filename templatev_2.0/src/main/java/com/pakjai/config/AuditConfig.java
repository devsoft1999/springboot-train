package com.pakjai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            // 👉 คุณสามารถดึง username จาก SecurityContext ได้ที่นี่
            // เช่น: SecurityContextHolder.getContext().getAuthentication().getName()
            return Optional.of("system"); // หรือ "anonymousUser"
        };
    }
}
