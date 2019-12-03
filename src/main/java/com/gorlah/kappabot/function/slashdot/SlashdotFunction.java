package com.gorlah.kappabot.function.slashdot;

import com.gorlah.kappabot.command.CommandMetadata;
import com.gorlah.kappabot.function.BotFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SlashdotFunction implements BotFunction {

    private final SlashdotParser slashdotParser;

    @Override
    public boolean shouldProcess(String message) {
        return slashdotParser.find(message);
    }

    @Override
    public String process(CommandMetadata metadata) {
        return slashdotParser.getTitles(metadata.getMessage()).stream()
                .map(title -> "> " + title)
                .collect(Collectors.joining("\n"));
    }
}
