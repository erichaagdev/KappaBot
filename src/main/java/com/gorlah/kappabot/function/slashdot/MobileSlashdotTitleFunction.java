package com.gorlah.kappabot.function.slashdot;

import com.gorlah.kappabot.function.BotFunction;
import com.gorlah.kappabot.function.BotRequestMetadata;
import com.gorlah.kappabot.function.response.BotResponse;
import com.gorlah.kappabot.function.response.BotResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MobileSlashdotTitleFunction implements BotFunction {

    private final MobileSlashdotTitleParser slashdotParser;

    @Override
    public Optional<BotResponse> process(BotRequestMetadata metadata) {
        return Optional.of(metadata)
                .map(BotRequestMetadata::getMessage)
                .map(slashdotParser::getTitles)
                .filter(titles -> !titles.isEmpty())
                .map(this::toMarkdown)
                .map(String::trim)
                .filter(response -> !response.isEmpty())
                .map(BotResponses::fromMarkdown);
    }

    private String toMarkdown(List<String> titles) {
        return titles.stream()
                .map(title -> "> " + title)
                .collect(Collectors.joining("\n"));
    }
}
