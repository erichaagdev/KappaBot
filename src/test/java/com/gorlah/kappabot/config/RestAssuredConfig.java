package com.gorlah.kappabot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static io.restassured.config.RestAssuredConfig.config;

@Configuration
@RequiredArgsConstructor
class RestAssuredConfig implements ApplicationListener<ServletWebServerInitializedEvent> {

    private final ObjectMapper objectMapper;

    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        RestAssured.port = event.getWebServer().getPort();
        RestAssured.config = config()
                .objectMapperConfig(objectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> objectMapper));
    }
}
