package com.gorlah.kappabot.integration.openweather;

import com.google.common.base.Strings;
import com.gorlah.kappabot.integration.KappaBotIntegration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class OpenWeatherIntegration implements KappaBotIntegration {

    @Value("${openweather.apiKey}")
    private String apiKey;

    @Override
    public String getName() {
        return "OpenWeather";
    }

    @Override
    public boolean isEnabled() {
        return !Strings.isNullOrEmpty(apiKey);
    }

    @Override
    public void initialize() {
        if (!isEnabled()) {
            log.warn("OpenWeather integration disabled! ('openweather.apiKey' property is not set)");
        }
    }

    public String getApiKey() {
        return apiKey;
    }
}
