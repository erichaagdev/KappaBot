package com.gorlah.kappabot.command;

import java.util.Collection;

public interface Command {

    String getName();

    String getHelpText();

    String getDetailedHelpText();

    String getErrorText();

    String getIncorrectUsageText();

    String getUsageInformation();

    String getAbsoluteCommandString();

    void setAbsoluteCommandString(String absoluteCommandString);

    boolean isShownInHelp();

    boolean isEnabled();

    boolean isParent();

    Class<?> getParent();

    Collection<Command> getChildren();

    Command getChild(String childName);

    void addChild(Command child);

    String process(CommandPayload payload);
}
