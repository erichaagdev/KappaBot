package com.gorlah.kappabot.subcommand.meme;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.command.Subcommand;
import com.gorlah.kappabot.subcommand.meme.me.MeButterfly;
import com.gorlah.kappabot.subcommand.meme.me.MeButtonSmash;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MemeMe extends Subcommand {
    
    public MemeMe(MeButterfly meButterfly,
                  MeButtonSmash meButtonSmash) {
        addSubcommand(meButterfly);
        addSubcommand(meButtonSmash);
    }
    
    @Override
    public String getName() {
        return "me";
    }
    
    @Override
    public String getHelpText() {
        return "Generates memes.";
    }
    
    @Override
    public String process(Command command, ArrayList<String> parameters) {
        return null;
    }
}