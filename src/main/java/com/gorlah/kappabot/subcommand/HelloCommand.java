package com.gorlah.kappabot.subcommand;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.command.Subcommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class HelloCommand extends Subcommand {
    
    @Override
    public String getName() {
        return "hello";
    }
    
    @Override
    public String getHelpText() {
        return "Displays a greeting to the user.";
    }
    
    @Override
    public String process(Command command, ArrayList<String> parameters) {
        if (parameters != null && !parameters.isEmpty() && "there".equals(parameters.get(0))) {
            return "General ${user.mention}!";
        }
        
        return "Hey, ${user.mention}!";
    }
}
