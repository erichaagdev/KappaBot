package com.gorlah.kappabot.integration.imgur;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ImgurImageUpload {

    private final ImgurIntegration imgurIntegration;
    private final ObjectMapper objectMapper;

    public String upload(ImgurImage image)
            throws JsonProcessingException {
        imgurIntegration.require();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Client-ID %s", imgurIntegration.getClientId()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(image), headers);

        String response = restTemplate.postForObject("https://api.imgur.com/3/upload", request, String.class);

        return objectMapper.readTree(Objects.requireNonNull(response)).get("data").get("link").textValue();
    }
}
