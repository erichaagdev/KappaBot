package com.gorlah.kappabot.function.command;

import com.gorlah.kappabot.function.BotRequestMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CommandAdapterFinder {

    private final Set<CommandAdapter> commandAdapters;

    public Optional<CommandAdapter> findAdapter(BotRequestMetadata metadata) {
        return commandAdapters.stream()
                .filter(CommandAdapter::isEnabled)
                .filter(adapter -> adapter.getSource().equals(metadata.getSource()))
                .filter(adapter -> shouldProcess(metadata, adapter.getCommandPrefix()))
                .findFirst();
    }

    private boolean shouldProcess(BotRequestMetadata metadata, String commandPrefix) {
        return metadata.getMessage().length() > commandPrefix.length()
                && messageBeginsWithCommandPrefix(metadata.getMessage(), commandPrefix);
    }

    private boolean messageBeginsWithCommandPrefix(String message, String commandPrefix) {
        return commandPrefix.equalsIgnoreCase(message.substring(0, commandPrefix.length()));
    }
}
