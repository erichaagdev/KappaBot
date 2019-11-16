package com.gorlah.kappabot.subcommand;

import com.gorlah.kappabot.command.Command;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CodeCommand extends Subcommand {
    
    @Override
    public String getName() {
        return "code";
    }
    
    @Override
    public String getHelpText() {
        return "Returns my GitHub repository url.";
    }
    
    @Override
    public boolean isShownInHelp() {
        return false;
    }
    
    @Override
    public String process(Command command, ArrayList<String> parameters) {
        return "My codebase is open source! Here's a link to my GitHub repository: https://github.com/Gorlah/KappaBot";
    }
}
