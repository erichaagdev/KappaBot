package com.gorlah.kappabot.subcommand;

import org.springframework.stereotype.Component;

@Component
public class MemeCommand extends Subcommand {

    @Override
    public String getName() {
        return "meme";
    }

    @Override
    public String getHelpText() {
        return "Create memes.";
    }

    @Override
    public boolean isShownInHelp() {
        return true;
    }

    @Override
    public Class<? extends Subcommand> getParent() {
        return RootCommand.class;
    }
}
