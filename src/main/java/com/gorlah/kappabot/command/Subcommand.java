package com.gorlah.kappabot.command;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

abstract public class Subcommand {
    
    private TreeMap<String, Subcommand> children = new TreeMap<>();
    
    abstract public String getName();
    
    abstract public String getHelpText();
    
    abstract public String process(Command command, ArrayList<String> parameters) throws Exception;
    
    String getHelp(String command) {
        StringBuilder builder = new StringBuilder(getHelpText());
    
        if (!children.isEmpty()) {
            builder.append(" Try adding one of the following subcommands to `")
                    .append(command)
                    .append("`:")
                    .append("```");
        
            int padding = children
                    .values()
                    .stream()
                    .mapToInt(subcommand -> subcommand.getName().length())
                    .max()
                    .orElse(0);
        
            for (Subcommand subcommand : children.values()) {
                builder.append("\n")
                        .append(StringUtils.leftPad(subcommand.getName(), padding))
                        .append(" - ")
                        .append(subcommand.getHelpText());
            }
            
            builder.append("```");
        
        }
    
        return builder.toString();
    }
    
    String getError() {
        return "Sorry, ${user.mention}. I'm not sure I understand.";
    }
    
    String onIncorrectUsage(String command) {
        StringBuilder builder = new StringBuilder("Try adding one of the following subcommands to `")
                .append(command)
                .append("`:");
        
        for (Map.Entry<String, Subcommand> entry : children.entrySet()) {
            builder.append(" ").append(entry.getKey()).append(" ,");
        }
        
        if (children.size() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        
        builder.append("\nOr use `")
                .append(command)
                .append(" help`");
        
        return builder.toString();
    }
    
    Subcommand getSubcommand(String subcommand) {
        return children.get(subcommand);
    }
    
    boolean hasSubcommands() {
        return !children.isEmpty();
    }
    
    protected void addSubcommand(Subcommand subcommand) {
        String subcommandName = subcommand.getName();
        
        if (this.children.containsKey(subcommandName)) {
            throw new IllegalArgumentException("Subcommand with name " + subcommandName + " already exists.");
        }
        
        this.children.put(subcommandName, subcommand);
    }
}
