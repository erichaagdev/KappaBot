package com.gorlah.kappabot.subcommand.weather;

import com.gorlah.kappabot.subcommand.Subcommand;
import com.gorlah.kappabot.subcommand.WeatherCommand;

abstract class WeatherSubcommand extends Subcommand {

    @Override
    public boolean isShownInHelp() {
        return true;
    }

    @Override
    public Class<? extends Subcommand> getParent() {
        return WeatherCommand.class;
    }
}
