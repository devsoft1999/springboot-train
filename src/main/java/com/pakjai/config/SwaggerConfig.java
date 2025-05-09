package com.pakjai.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Pakjai API Documentation",
        version = "1.0",
        description = "API documentation for the Pakjai Burnout Checker App"
    )
)
public class SwaggerConfig {
}
