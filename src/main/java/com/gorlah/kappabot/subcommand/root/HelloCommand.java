package com.gorlah.kappabot.subcommand.root;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.command.CommandPayload;
import com.gorlah.kappabot.subcommand.RootCommand;
import com.gorlah.kappabot.subcommand.SkeletalKappaBotCommand;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class HelloCommand extends SkeletalKappaBotCommand {

    @Override
    public String getName() {
        return "hello";
    }

    @Override
    public String getHelpText() {
        return "Displays a greeting to the user.";
    }

    @Override
    public Class<? extends Command> getParent() {
        return RootCommand.class;
    }

    @Override
    public String process(CommandPayload payload) {
        val parameters = payload.getParameters();

        if (parameters != null && !parameters.isEmpty() && "there".equals(parameters.get(0))) {
            return "General ${user.mention}!";
        }

        return "Hey, ${user.mention}!";
    }
}
