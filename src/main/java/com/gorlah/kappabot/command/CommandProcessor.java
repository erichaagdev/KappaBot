package com.gorlah.kappabot.command;


import com.gorlah.kappabot.subcommand.RootCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CommandProcessor {
    
    private static final Logger logger = LoggerFactory.getLogger(CommandProcessor.class);
    
    private final RootCommand rootCommand;
    
    private CommandProcessor(RootCommand rootCommand) {
        this.rootCommand = rootCommand;
    }
    
    public String process(Command command) {
        Subcommand currentSubcommand = rootCommand;
        Subcommand nextSubcommand;
        
        StringBuilder commandString = new StringBuilder(currentSubcommand.getName());
        ArrayList<String> parameters = new ArrayList<>();
        
        command.beforeFirst();
        while (command.next()) {
            String subcommandString = command.getSubcommandString();
            
            if (parameters.isEmpty() && "help".equals(subcommandString)) {
                return currentSubcommand.getHelp(commandString.toString());
            }
            
            nextSubcommand = currentSubcommand.getSubcommand(subcommandString);
            
            if (nextSubcommand == null) {
                parameters.add(command.getSubcommandString());
            } else {
                commandString.append(" ")
                        .append(subcommandString);
                currentSubcommand = nextSubcommand;
            }
        }
        
        if (currentSubcommand.hasSubcommands()) {
            return currentSubcommand.onIncorrectUsage(commandString.toString());
        }
        
        try {
            return currentSubcommand.process(command, parameters);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return currentSubcommand.getError();
        }
    }
}
