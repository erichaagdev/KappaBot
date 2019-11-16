package com.gorlah.kappabot.subcommand;

import com.gorlah.kappabot.command.Command;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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

    @Override
    public String process(Command command, ArrayList<String> parameters) {
        return null;
    }
}
