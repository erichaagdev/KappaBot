package com.gorlah.kappabot.integration.openweather;

import com.gorlah.kappabot.integration.openweather.model.OpenWeatherResponse;
import com.gorlah.kappabot.integration.openweather.model.WeatherDescription;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
class OpenWeatherMessageFormatter {

    String format(OpenWeatherResponse weatherResponse) {
        return String.format(
                "Current weather for %s: %s°f %s (▲%s°f ▼%s°f)",
                weatherResponse.getCity(),
                weatherResponse.getDetails().getCurrentTemperature().intValue(),
                weatherResponse.getDescriptions().stream()
                        .map(WeatherDescription::getDescription)
                        .collect(Collectors.joining(weatherResponse.getDescriptions().size() == 2 ? " & " : ", ")),
                weatherResponse.getDetails().getMaximumTemperature().intValue(),
                weatherResponse.getDetails().getMinimumTemperature().intValue());
    }
}
