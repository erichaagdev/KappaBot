package com.gorlah.kappabot.subcommand.root;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.command.CommandPayload;
import com.gorlah.kappabot.subcommand.RootCommand;
import com.gorlah.kappabot.subcommand.SkeletalKappaBotCommand;
import org.springframework.stereotype.Component;

@Component
public class CodeCommand extends SkeletalKappaBotCommand {

    @Override
    public String getName() {
        return "code";
    }

    @Override
    public String getHelpText() {
        return "Returns my GitHub repository url.";
    }

    @Override
    public Class<? extends Command> getParent() {
        return RootCommand.class;
    }

    @Override
    public String process(CommandPayload payload) {
        return "My codebase is open source! Here's a link to my GitHub repository: https://github.com/Gorlah/KappaBot";
    }
}
