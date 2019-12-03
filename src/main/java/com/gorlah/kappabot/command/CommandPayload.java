package com.gorlah.kappabot.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandPayload {
    private static final Pattern REGEX = Pattern.compile("\"[^\"\\\\]*(?:\\\\.[^\"\\\\]*)*\"|\\S+");

    private ArrayList<String> command;

    private ArrayList<String> parameters = new ArrayList<>();

    private String calledBy;

    private int commandPointer;

    public CommandPayload(String prefix, String command, String calledBy) {
        this.command = parseCommand(prefix, command);
        this.commandPointer = -1;
        this.calledBy = calledBy;
    }

    public List<String> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    public void addParameter(String parameter) {
        this.parameters.add(parameter);
    }

    public String getCalledBy() {
        return calledBy;
    }

    void beforeFirst() {
        commandPointer = -1;
    }

    boolean next() {
        return (++commandPointer) < command.size();
    }

    String getSubcommandString() {
        return command.get(commandPointer);
    }

    private ArrayList<String> parseCommand(String prefix, String command) {
        if (command.length() == prefix.length()) {
            command = "";
        } else {
            command = command.substring(prefix.length() + 1);
        }

        ArrayList<String> messageList = new ArrayList<>();

        Matcher regexMatcher = REGEX.matcher(command);

        while (regexMatcher.find()) {
            String messageToAdd = regexMatcher.group();

            if (!messageToAdd.isEmpty() && messageToAdd.charAt(0) == '\"') {
                messageToAdd = messageToAdd.substring(1);
            }

            if (!messageToAdd.isEmpty() && messageToAdd.charAt(messageToAdd.length() - 1) == '\"') {
                messageToAdd = messageToAdd.substring(0, messageToAdd.length() - 1);
            }

            messageList.add(messageToAdd.replaceAll("\\\\\"", "\""));
        }

        return messageList;
    }
}
