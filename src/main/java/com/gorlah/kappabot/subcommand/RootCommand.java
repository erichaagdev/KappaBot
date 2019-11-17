package com.gorlah.kappabot.subcommand;

import org.springframework.stereotype.Component;

@Component
public class RootCommand extends Subcommand {

    @Override
    public String getName() {
        return "/kb";
    }

    @Override
    public String getHelpText() {
        return "Hey, ${user.mention}! My name is KappaBot.";
    }

    @Override
    public boolean isShownInHelp() {
        return true;
    }

    @Override
    public Class<? extends Subcommand> getParent() {
        throw new UnsupportedOperationException();
    }
}
