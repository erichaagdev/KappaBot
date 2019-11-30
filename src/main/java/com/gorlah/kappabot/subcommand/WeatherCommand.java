package com.gorlah.kappabot.subcommand;

import com.gorlah.kappabot.integration.openweather.OpenWeatherIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class WeatherCommand extends Subcommand {

    private final OpenWeatherIntegration openWeatherIntegration;

    @Override
    public String getName() {
        return "weather";
    }

    @Override
    public String getHelpText() {
        return "Gets information about the weather.";
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
}
