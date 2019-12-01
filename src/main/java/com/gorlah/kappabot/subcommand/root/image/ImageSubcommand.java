package com.gorlah.kappabot.subcommand.root.image;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.subcommand.SkeletalKappaBotCommand;
import com.gorlah.kappabot.subcommand.root.ImageCommand;
import org.springframework.stereotype.Component;

@Component
public abstract class ImageSubcommand extends SkeletalKappaBotCommand {

    @Override
    public Class<? extends Command> getParent() {
        return ImageCommand.class;
    }
}
