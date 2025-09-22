package com.shashankpk31.aiskillmatch_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AISkillMatch Backend API")
                        .version("0.0.1-SNAPSHOT")
                        .description("Enhanced Job Portal Backend API Documentation"));
    }
}
