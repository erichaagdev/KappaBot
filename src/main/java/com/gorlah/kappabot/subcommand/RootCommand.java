package com.gorlah.kappabot.subcommand;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.command.Subcommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RootCommand extends Subcommand {
    
    public RootCommand(HelloCommand helloCommand,
                       ImageCommand imageCommand,
                       MemeCommand memeCommand) {
        addSubcommand(helloCommand);
        addSubcommand(imageCommand);
        addSubcommand(memeCommand);
    }
    
    @Override
    public String getName() {
        return "/kb";
    }
    
    @Override
    public String getHelpText() {
        return "Hey, ${user.mention}! My name is KappaBot.";
    }
    
    @Override
    public String process(Command command, ArrayList<String> parameters) {
        return null;
    }
}
