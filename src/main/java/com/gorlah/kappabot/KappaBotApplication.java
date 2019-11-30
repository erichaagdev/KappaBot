package com.gorlah.kappabot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class KappaBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(KappaBotApplication.class, args);
    }

    @Bean
    @Scope("prototype")
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
