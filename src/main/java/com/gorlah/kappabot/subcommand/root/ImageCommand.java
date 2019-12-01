package com.gorlah.kappabot.subcommand.root;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.subcommand.RootCommand;
import com.gorlah.kappabot.subcommand.SkeletalKappaBotCommand;
import org.springframework.stereotype.Component;

@Component
public class ImageCommand extends SkeletalKappaBotCommand {

    @Override
    public String getName() {
        return "image";
    }

    @Override
    public String getHelpText() {
        return "For everything related to images.";
    }

    @Override
    public Class<? extends Command> getParent() {
        return RootCommand.class;
    }
}
