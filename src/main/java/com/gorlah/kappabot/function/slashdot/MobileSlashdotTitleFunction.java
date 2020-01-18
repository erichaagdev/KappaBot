package com.gorlah.kappabot.function.slashdot;

import com.gorlah.kappabot.function.BotFunction;
import com.gorlah.kappabot.function.BotRequestMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MobileSlashdotTitleFunction implements BotFunction {

    private final MobileSlashdotTitleParser slashdotParser;

    @Override
    public Optional<String> process(BotRequestMetadata metadata) {
        if (slashdotParser.containsMobileSlashdotUrl(metadata.getMessage())) {
            var response = slashdotParser.getTitles(metadata.getMessage()).stream()
                    .map(title -> "> " + title)
                    .collect(Collectors.joining("\n"));
            if (!response.trim().isEmpty()) {
                return Optional.of(response);
            }
        }
        return Optional.empty();
    }
}
