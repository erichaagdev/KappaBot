package com.gorlah.kappabot.subcommand.root.meme;

import com.gorlah.kappabot.subcommand.SkeletalKappaBotCommand;
import com.gorlah.kappabot.subcommand.root.MemeCommand;
import com.gorlah.kappabot.command.Command;
import org.springframework.stereotype.Component;

@Component
public class MemeTest extends SkeletalKappaBotCommand {

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getHelpText() {
        return "Generates a meme and writes it to a local file.";
    }

    @Override
    public boolean isShownInHelp() {
        return false;
    }

    @Override
    public Class<? extends Command> getParent() {
        return MemeCommand.class;
    }
}
