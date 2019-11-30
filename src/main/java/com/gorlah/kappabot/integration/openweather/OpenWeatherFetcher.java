package com.gorlah.kappabot.integration.openweather;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.common.primitives.Longs;
import com.gorlah.kappabot.integration.openweather.model.OpenWeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Duration;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenWeatherFetcher {

    private static final String API_BASE = "https://api.openweathermap.org/data/2.5/weather";
    private static final String API_KEY_PARAM = "APPID";
    private static final String ZIP_CODE_PARAM = "zip";
    private static final String CITY_PARAM = "q";
    private static final String UNITS_PARAM = "units";
    private static final String UNITS_IMPERIAL = "imperial";

    @Value("${kb.user-agent}")
    private String userAgent;

    private final RestTemplate restTemplate;
    private final OpenWeatherMessageFormatter openWeatherMessageFormatter;
    private final OpenWeatherIntegration openWeatherIntegration;

    private final LoadingCache<String, OpenWeatherResponse> weatherCache =
            Caffeine.newBuilder()
                    .expireAfterWrite(Duration.ofMinutes(15))
                    .build(this::doFetchWeather);

    public String fetchWeather(String zipCodeOrCityName) {
        openWeatherIntegration.require();
        try {
            return openWeatherMessageFormatter.format(Objects.requireNonNull(weatherCache.get(zipCodeOrCityName)));
        } catch (Exception ex) {
            var errorMsg = "Failed to get weather for: " + zipCodeOrCityName;
            log.error(errorMsg, ex);
            return errorMsg;
        }
    }

    private OpenWeatherResponse doFetchWeather(String zipCodeOrCityName) {
        if (zipCodeOrCityName.length() == 5 && Longs.tryParse(zipCodeOrCityName) != null) {
            return fetchByZipCode(zipCodeOrCityName);
        }
        return fetchByCity(zipCodeOrCityName);
    }

    private OpenWeatherResponse fetchByZipCode(String zipCode) {
        return fetch(getUri(ZIP_CODE_PARAM, zipCode));
    }

    private OpenWeatherResponse fetchByCity(String cityName) {
        return fetch(getUri(CITY_PARAM, cityName));
    }

    private OpenWeatherResponse fetch(URI uri) {
        return restTemplate.exchange(uri, HttpMethod.GET, createRequestEntity(), OpenWeatherResponse.class).getBody();
    }

    private URI getUri(String paramName, String paramValue) {
        return UriComponentsBuilder.fromHttpUrl(API_BASE)
                .queryParam(paramName, paramValue)
                .queryParam(API_KEY_PARAM, openWeatherIntegration.getApiKey())
                .queryParam(UNITS_PARAM, UNITS_IMPERIAL)
                .build()
                .toUri();
    }

    private HttpEntity createRequestEntity() {
        var headers = new HttpHeaders();
        headers.set(HttpHeaders.USER_AGENT, userAgent);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}
