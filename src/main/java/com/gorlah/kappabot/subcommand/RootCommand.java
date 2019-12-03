package com.gorlah.kappabot.subcommand;

import com.gorlah.kappabot.command.Command;
import org.springframework.stereotype.Component;

@Component
public class RootCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "/kb";
    }

    @Override
    public String getHelpText() {
        return "Hey, ${user.mention}! My name is KappaBot.";
    }

    @Override
    public String getUsageInformation() {
        return null;
    }

    @Override
    public boolean isShownInHelp() {
        return true;
    }

    @Override
    public Class<? extends Command> getParent() {
        throw new UnsupportedOperationException();
    }
}
