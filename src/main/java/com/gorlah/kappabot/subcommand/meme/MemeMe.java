package com.gorlah.kappabot.subcommand.meme;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.subcommand.MemeCommand;
import com.gorlah.kappabot.subcommand.Subcommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MemeMe extends Subcommand {
    
    @Override
    public String getName() {
        return "me";
    }
    
    @Override
    public String getHelpText() {
        return "Generates a meme.";
    }
    
    @Override
    public boolean isShownInHelp() {
        return true;
    }

    @Override
    public Class<? extends Subcommand> getParent() {
        return MemeCommand.class;
    }

    @Override
    public String process(Command command, ArrayList<String> parameters) {
        return null;
    }
}