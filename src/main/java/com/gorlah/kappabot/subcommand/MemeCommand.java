package com.gorlah.kappabot.subcommand;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.subcommand.meme.MemeMe;
import com.gorlah.kappabot.subcommand.meme.MemeTest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MemeCommand extends Subcommand {
    
    public MemeCommand(MemeMe memeMe,
                       MemeTest memeTest) {
        addSubcommand(memeMe);
        addSubcommand(memeTest);
    }
    
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
    public String process(Command command, ArrayList<String> parameters) {
        return null;
    }
}
