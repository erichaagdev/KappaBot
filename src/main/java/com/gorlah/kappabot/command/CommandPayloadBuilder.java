package com.gorlah.kappabot.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandPayloadBuilder {

    private final Pattern REGEX = Pattern.compile("\"[^\"\\\\]*(?:\\\\.[^\"\\\\]*)*\"|\\S+");

    @Value("${discord.command.prefix}")
    private String commandPrefix;

    public CommandPayload parseMessageAndBuild(CommandMetadata metadata) {
        return new CommandPayload(parseMessage(metadata.getMessage()), metadata);
    }

    private List<String> parseMessage(String message) {
        ArrayList<String> messageList = new ArrayList<>();
        Matcher regexMatcher = REGEX.matcher(stripCommandPrefix(message));

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

    private String stripCommandPrefix(String message) {
        if (message.length() == commandPrefix.length()) {
            return "";
        }

        return message.substring(commandPrefix.length() + 1);
    }
}
