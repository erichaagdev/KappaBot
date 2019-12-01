package com.gorlah.kappabot.command;

import java.util.Collection;

/**
 * This interface outlines the methods required for an object to represent a command.
 */
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

    /**
     * Returns the parent class in which this command is a child of.
     *
     * @return the parent class in which this command is a child of.
     */
    Class<?> getParent();

    Collection<Command> getChildren();

    Command getChild(String childName);

    void addChild(Command child);

    /**
     * The method to call for processing this command.
     *
     * @param payload a payload containing information which may be useful or required for processing this command
     * @return the text to display generated from this command
     */
    String process(CommandPayload payload);
}
