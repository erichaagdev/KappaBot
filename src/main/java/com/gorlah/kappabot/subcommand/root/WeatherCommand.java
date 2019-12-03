package com.gorlah.kappabot.subcommand.root;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.integration.openweather.OpenWeatherIntegration;
import com.gorlah.kappabot.subcommand.RootCommand;
import com.gorlah.kappabot.subcommand.AbstractCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class WeatherCommand extends AbstractCommand {

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
    public Class<? extends Command> getParent() {
        return RootCommand.class;
    }

    @Override
    public boolean isEnabled() {
        return openWeatherIntegration.isEnabled();
    }
}
