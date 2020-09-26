package com.gorlah.kappabot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenApiConfig {

    @Bean
    OpenAPI openApi(GitProperties gitProperties) {
        return new OpenAPI()
                .info(info(gitProperties));
    }

    private Info info(GitProperties gitProperties) {
        return new Info()
                .title("KappaBot")
                .description("Exposes endpoint for testing KappaBot commands.")
                .version(gitProperties.getBranch());
    }
}
