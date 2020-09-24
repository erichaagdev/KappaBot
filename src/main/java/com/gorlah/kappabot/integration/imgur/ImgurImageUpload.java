package com.gorlah.kappabot.integration.imgur;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImgurImageUpload {

    private final ImgurIntegration imgurIntegration;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public Optional<String> upload(ImgurImage image) {
        imgurIntegration.require();

        try {
            var headers = new HttpHeaders();
            headers.add("Authorization", String.format("Client-ID %s", imgurIntegration.getClientId()));
            headers.setContentType(MediaType.APPLICATION_JSON);

            var request = new HttpEntity<>(objectMapper.writeValueAsString(image), headers);
            var response = restTemplate.postForObject("https://api.imgur.com/3/upload", request, String.class);
            var imageUrl = objectMapper.readTree(Objects.requireNonNull(response))
                    .get("data")
                    .get("link")
                    .textValue();

            return Optional.of(imageUrl);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }
}
