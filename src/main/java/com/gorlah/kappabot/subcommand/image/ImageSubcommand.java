package com.gorlah.kappabot.subcommand.image;

import com.gorlah.kappabot.subcommand.ImageCommand;
import com.gorlah.kappabot.subcommand.Subcommand;
import org.springframework.stereotype.Component;

@Component
public abstract class ImageSubcommand extends Subcommand {

    @Override
    public Class<? extends Subcommand> getParent() {
        return ImageCommand.class;
    }

    @Override
    public boolean isShownInHelp() {
        return true;
    }
}
