package com.klef.soa.docker.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI volunteerActivityOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Volunteer Activity Management API")
                .version("1.0")
                .description("REST API to create, view and delete volunteer activities"));
    }
}
