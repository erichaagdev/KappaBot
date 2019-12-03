package com.gorlah.kappabot.subcommand.root.weather;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.subcommand.AbstractCommand;
import com.gorlah.kappabot.subcommand.root.WeatherCommand;

abstract class WeatherSubcommand extends AbstractCommand {

    @Override
    public boolean isShownInHelp() {
        return true;
    }

    @Override
    public Class<? extends Command> getParent() {
        return WeatherCommand.class;
    }
}
