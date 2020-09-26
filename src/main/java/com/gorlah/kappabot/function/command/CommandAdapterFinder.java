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
                .filter(adapter -> metadata.getMessage().startsWith(adapter.getCommandPrefix()))
                .findFirst();
    }
}
