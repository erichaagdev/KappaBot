package com.gorlah.kappabot.function.command;

import com.google.common.base.Strings;
import com.gorlah.kappabot.command.CommandPayloadBuilder;
import com.gorlah.kappabot.command.CommandProcessor;
import com.gorlah.kappabot.function.BotFunction;
import com.gorlah.kappabot.function.BotRequestMetadata;
import com.gorlah.kappabot.function.response.BotResponse;
import com.gorlah.kappabot.function.response.ImmutableBotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommandFunction implements BotFunction {

    private final CommandPayloadBuilder commandPayloadBuilder;
    private final CommandProcessor commandProcessor;
    private final CommandAdapterFinder commandAdapterFinder;

    @Override
    public Optional<BotResponse> process(BotRequestMetadata metadata) {
        return commandAdapterFinder.findAdapter(metadata)
                .map(CommandAdapter::getCommandPrefix)
                .map(commandPrefix -> commandPayloadBuilder.parseMessageAndBuild(metadata, commandPrefix))
                .map(commandProcessor::process)
                .map(Strings::nullToEmpty)
                .map(message -> ImmutableBotResponse.of("text/markdown", message));
    }
}
