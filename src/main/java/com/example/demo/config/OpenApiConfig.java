package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            // Change the port as per your server
            .servers(List.of(
                new Server().url("https://9110.32procr.amypo.ai")
            ))
            .info(new Info()
                .title("Apartment Facility Booking System API")
                .version("1.0")
                .description("API for managing apartment facility bookings, user registration, apartment assignment, and booking logs"))
            .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
            .components(new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes("Bearer Authentication", 
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));
    }
}