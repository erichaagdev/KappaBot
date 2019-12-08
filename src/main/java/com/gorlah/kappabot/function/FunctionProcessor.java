package com.gorlah.kappabot.function;

import com.gorlah.kappabot.message.ResponseMarkdownReplacer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FunctionProcessor {

    private final Set<BotFunction> botFunctions;
    private final ResponseMetadataReplacer metadataReplacer;
    private final ResponseMarkdownReplacer markdownReplacer;

    public String process(BotRequestMetadata metadata) {
        return botFunctions.stream()
                .filter(botFunction -> botFunction.shouldProcess(metadata.getMessage()))
                .map(botFunction -> botFunction.process(metadata))
                .map(response -> metadataReplacer.replace(response, metadata))
                .map(response -> markdownReplacer.replace(response, metadata.getEndpoint()))
                .collect(Collectors.joining("\n"));
    }
}
