package com.gorlah.kappabot.subcommand;

import com.gorlah.kappabot.command.Command;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

abstract public class Subcommand {

    private TreeMap<String, Subcommand> children = new TreeMap<>();

    abstract public String getName();

    abstract public String getHelpText();

    abstract public boolean isShownInHelp();

    abstract public Class<? extends Subcommand> getParent();

    public boolean isEnabled() {
        return true;
    }

    public String process(Command command, ArrayList<String> parameters) throws Exception {
        return null;
    }

    public String getHelp(String command) {
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
                if (subcommand.isShownInHelp()) {
                    builder.append("\n")
                        .append(StringUtils.leftPad(subcommand.getName(), padding))
                        .append(" - ")
                        .append(subcommand.getHelpText());
                }
            }

            builder.append("```");

        }

        return builder.toString();
    }

    public String getError() {
        return "Sorry, ${user.mention}. I ran into a problem processing your command.";
    }

    public String onIncorrectUsage(String command) {
        StringBuilder builder = new StringBuilder("Try adding one of the following subcommands to `")
                .append(command)
                .append("`:");

        for (Subcommand subcommand : children.values()) {
            if (subcommand.isShownInHelp()) {
                builder.append(" ").append(subcommand.getName()).append(" ,");
            }
        }

        if (children.size() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        builder.append("\nOr use `")
                .append(command)
                .append(" help`");

        return builder.toString();
    }

    public Subcommand getSubcommand(String subcommand) {
        return children.get(subcommand.toLowerCase());
    }

    public boolean hasSubcommands() {
        return !children.isEmpty();
    }

    void addSubcommand(Subcommand subcommand) {
        String subcommandName = subcommand.getName().toLowerCase();

        if (this.children.containsKey(subcommandName)) {
            throw new IllegalArgumentException(
                "Subcommand with name " + subcommandName + " already exists in " + getName());
        }

        this.children.put(subcommandName, subcommand);
    }

    Map<String, Subcommand> getChildren() {
        return Collections.unmodifiableMap(children);
    }
}
