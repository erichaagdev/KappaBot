package com.gorlah.kappabot.subcommand;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.command.Subcommand;
import com.gorlah.kappabot.subcommand.meme.MemeMe;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MemeCommand extends Subcommand {
    
    public MemeCommand(MemeMe memeMe) {
        addSubcommand(memeMe);
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
    public String process(Command command, ArrayList<String> parameters) throws Exception {
        return null;
    }
}
