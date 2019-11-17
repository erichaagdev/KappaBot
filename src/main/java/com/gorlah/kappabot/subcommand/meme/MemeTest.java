package com.gorlah.kappabot.subcommand.meme;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.subcommand.MemeCommand;
import com.gorlah.kappabot.subcommand.Subcommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MemeTest extends Subcommand {

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
    public Class<? extends Subcommand> getParent() {
        return MemeCommand.class;
    }

    @Override
    public String process(Command command, ArrayList<String> parameters) {
        return null;
    }
}
