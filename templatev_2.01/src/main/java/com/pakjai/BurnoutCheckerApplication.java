package com.pakjai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import lombok.extern.slf4j.Slf4j;

@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@SpringBootApplication
@Slf4j
public class BurnoutCheckerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BurnoutCheckerApplication.class, args);
        log.trace("TRACE BurnoutCheckerApplication started");
        log.debug("DEBUG BurnoutCheckerApplication started");
        log.info("INFO BurnoutCheckerApplication started");
        log.warn("WARN BurnoutCheckerApplication started");
        log.error("ERROR BurnoutCheckerApplication started");
    }
}