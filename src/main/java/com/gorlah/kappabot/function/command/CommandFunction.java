package com.gorlah.kappabot.function.command;

import com.gorlah.kappabot.command.CommandMetadata;
import com.gorlah.kappabot.command.CommandPayloadBuilder;
import com.gorlah.kappabot.command.CommandProcessor;
import com.gorlah.kappabot.function.BotFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandFunction implements BotFunction {

    @Value("${discord.command.prefix}")
    private String commandPrefix;

    private final CommandPayloadBuilder commandPayloadBuilder;
    private final CommandProcessor commandProcessor;

    @Override
    public boolean shouldProcess(String message) {
        return message.length() > commandPrefix.length() && messageBeginsWithCommandPrefix(message);
    }

    @Override
    public String process(CommandMetadata metadata) {
        return commandProcessor.process(commandPayloadBuilder.parseMessageAndBuild(metadata));
    }

    private boolean messageBeginsWithCommandPrefix(String message) {
        return commandPrefix.equalsIgnoreCase(message.substring(0, commandPrefix.length()));
    }
}
