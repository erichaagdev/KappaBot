package com.gorlah.kappabot.function;

import com.google.common.collect.ImmutableList;
import com.gorlah.kappabot.function.response.formatter.BotResponseFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class FunctionProcessor {

    private final Set<BotFunction> botFunctions;
    private final BotResponseFormatter botResponseFormatter;

    public List<String> process(BotRequestMetadata metadata) {
        return botFunctions.stream()
                .map(botFunction -> botFunction.process(metadata))
                .flatMap(Optional::stream)
                .map(response -> botResponseFormatter.format(metadata, response))
                .collect(ImmutableList.toImmutableList());
    }
}
