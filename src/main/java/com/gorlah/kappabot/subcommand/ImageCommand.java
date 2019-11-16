package com.gorlah.kappabot.subcommand;

import com.gorlah.kappabot.command.Command;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ImageCommand extends Subcommand {

    @Override
    public String getName() {
        return "image";
    }
    
    @Override
    public String getHelpText() {
        return "For everything related to images.";
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
