package com.gorlah.kappabot.subcommand.root.weather;

import com.google.common.base.Joiner;
import com.gorlah.kappabot.command.CommandPayload;
import com.gorlah.kappabot.integration.openweather.OpenWeatherFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyWeatherSubcommand extends WeatherSubcommand {

    private final OpenWeatherFetcher openWeatherFetcher;

    @Override
    public String getName() {
        return "daily";
    }

    @Override
    public String getHelpText() {
        return "Gets the daily weather by ZIP Code™ or city name.";
    }

    @Override
    public String process(CommandPayload payload) {
        var zipCodeOrCityName = Joiner.on(' ').join(payload.getParameters());

        if (zipCodeOrCityName.isBlank()) {
            return "Must specify a ZIP Code™ or city name!";
        }

        return openWeatherFetcher.fetchWeather(zipCodeOrCityName);
    }
}
