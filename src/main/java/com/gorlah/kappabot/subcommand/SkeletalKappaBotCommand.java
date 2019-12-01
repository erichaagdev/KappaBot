package com.gorlah.kappabot.subcommand;


import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.command.CommandPayload;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.TreeMap;

public abstract class SkeletalKappaBotCommand implements Command {

    private TreeMap<String, Command> children = new TreeMap<>();

    @Getter
    @Setter
    private String absoluteCommandString;

    @Override
    public String getDetailedHelpText() {
        StringBuilder builder = new StringBuilder(getHelpText());

        if (!children.isEmpty()) {
            builder.append(" Try adding one of the following subcommands to `")
                    .append(getAbsoluteCommandString())
                    .append("`:")
                    .append("```");

            int padding = children
                    .values()
                    .stream()
                    .mapToInt(subcommand -> subcommand.getName().length())
                    .max()
                    .orElse(0);

            for (Command subcommand : children.values()) {
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

    @Override
    public String getErrorText() {
        return "Sorry, ${user.mention}. I ran into a problem processing your command.";
    }

    @Override
    public String getIncorrectUsageText() {
        StringBuilder builder = new StringBuilder("Try adding one of the following subcommands to `")
                .append(getAbsoluteCommandString())
                .append("`:");

        children.values().stream()
                .filter(Command::isShownInHelp)
                .forEach(subcommand -> builder.append(" ").append(subcommand.getName()).append(" ,"));

        if (children.size() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        builder.append("\nOr use `")
                .append(getAbsoluteCommandString())
                .append(" help`");

        return builder.toString();
    }

    @Override
    public String getUsageInformation() {
        return "";
    }

    @Override
    public boolean isShownInHelp() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isParent() {
        return !children.isEmpty();
    }

    @Override
    public Collection<Command> getChildren() {
        return Collections.unmodifiableCollection(children.values());
    }

    @Override
    public Command getChild(String childName) {
        return children.values().stream()
                .filter(child -> Objects.equals(childName, child.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addChild(Command child) {
        String subcommandName = child.getName().toLowerCase();

        if (this.children.containsKey(subcommandName)) {
            throw new IllegalArgumentException(
                    "Command with name " + subcommandName + " already exists in " + getName());
        }

        this.children.put(subcommandName, child);
    }

    @Override
    public String process(CommandPayload payload) {
        return getIncorrectUsageText();
    }
}
