package com.gorlah.kappabot.subcommand.root;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.subcommand.RootCommand;
import com.gorlah.kappabot.subcommand.AbstractCommand;
import org.springframework.stereotype.Component;

@Component
public class MemeCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "meme";
    }

    @Override
    public String getHelpText() {
        return "Create memes.";
    }

    @Override
    public Class<? extends Command> getParent() {
        return RootCommand.class;
    }
}
