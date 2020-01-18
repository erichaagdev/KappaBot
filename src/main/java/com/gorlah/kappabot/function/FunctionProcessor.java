package com.gorlah.kappabot.function;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FunctionProcessor {

    private final Set<BotFunction> botFunctions;

    public String process(BotRequestMetadata metadata) {
        return botFunctions.stream()
                .map(botFunction -> botFunction.process(metadata))
                .flatMap(Optional::stream)
                .collect(Collectors.joining("\n"));
    }
}
