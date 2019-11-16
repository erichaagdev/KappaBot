package com.gorlah.kappabot.imgur;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class ImgurImageUpload {
    
    @Value("${imgur.clientId}")
    private String imgurClientId;
    
    private final ObjectMapper objectMapper;
    
    public ImgurImageUpload(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    public String upload(ImgurImage image) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Client-ID %s", imgurClientId));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(image), headers);

        String response = restTemplate.postForObject("https://api.imgur.com/3/upload", request, String.class);

        return objectMapper.readTree(Objects.requireNonNull(response)).get("data").get("link").textValue();
    }
}
