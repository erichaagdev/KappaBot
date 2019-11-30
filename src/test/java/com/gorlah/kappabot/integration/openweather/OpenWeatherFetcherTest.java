package com.gorlah.kappabot.integration.openweather;

import com.gorlah.kappabot.integration.openweather.model.OpenWeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SuppressWarnings("unchecked")
class OpenWeatherFetcherTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private OpenWeatherMessageFormatter openWeatherMessageFormatter;
    @Mock
    private OpenWeatherIntegration openWeatherIntegration;

    private OpenWeatherFetcher openWeatherFetcher;

    @BeforeEach
    void setUp() {
        openWeatherFetcher = new OpenWeatherFetcher(restTemplate, openWeatherMessageFormatter, openWeatherIntegration);
    }

    @Test
    void testFetchWeather() {
        when(openWeatherIntegration.getApiKey()).thenReturn("5555555555");
        var expected = "Current weather for Nowhere Ville: 22°f cloudy & chance of meatballs (▲33°f ▼11°f)";
        var responseEntity = (ResponseEntity<OpenWeatherResponse>) mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(OpenWeatherTestUtil.getExampleResponseA());
        var uri = URI.create("https://api.openweathermap.org/data/2.5/weather?zip=12345&APPID=5555555555&units=imperial");
        var headers = new HttpHeaders();
        headers.set(HttpHeaders.USER_AGENT, null);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        when(restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), OpenWeatherResponse.class))
                .thenReturn(responseEntity);
        when(openWeatherMessageFormatter.format(OpenWeatherTestUtil.getExampleResponseA())).thenReturn(expected);
        assertEquals(expected, openWeatherFetcher.fetchWeather("12345"));
    }
}
