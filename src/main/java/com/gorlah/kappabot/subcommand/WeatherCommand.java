package com.gorlah.kappabot.subcommand;

import com.google.common.base.Joiner;
import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.integration.openweather.OpenWeatherFetcher;
import com.gorlah.kappabot.integration.openweather.OpenWeatherIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
@RequiredArgsConstructor
public class WeatherCommand extends Subcommand {

    private final OpenWeatherIntegration openWeatherIntegration;
    private final OpenWeatherFetcher openWeatherFetcher;

    @Override
    public String getName() {
        return "weather";
    }

    @Override
    public String getHelpText() {
        return "Gets the weather by ZIP Code™ or city name.";
    }

    @Override
    public boolean isShownInHelp() {
        return true;
    }

    @Override
    public Class<? extends Subcommand> getParent() {
        return RootCommand.class;
    }

    @Override
    public boolean isEnabled() {
        return openWeatherIntegration.isEnabled();
    }

    @Override
    public String process(Command command, ArrayList<String> parameters) {
        var zipCodeOrCityName = Joiner.on(' ').join(parameters);
        if (zipCodeOrCityName.isBlank()) {
            return "Must specify a ZIP Code™ or city name!";
        }
        return openWeatherFetcher.fetchWeather(zipCodeOrCityName);
    }
}
