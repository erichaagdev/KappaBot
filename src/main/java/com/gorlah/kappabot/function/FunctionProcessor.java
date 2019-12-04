package com.gorlah.kappabot.function;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FunctionProcessor {

    private final Set<BotFunction> botFunctions;

    public String process(BotRequestMetadata metadata) {
        return botFunctions.stream()
                .filter(botFunction -> botFunction.shouldProcess(metadata.getMessage()))
                .map(botFunction -> botFunction.process(metadata))
                .collect(Collectors.joining("\n"));
    }
}
