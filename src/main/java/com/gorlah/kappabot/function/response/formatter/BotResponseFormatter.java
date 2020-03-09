package com.gorlah.kappabot.function.response.formatter;

import com.gorlah.kappabot.function.BotRequestMetadata;
import com.gorlah.kappabot.function.command.CommandAdapter;
import com.gorlah.kappabot.function.command.CommandAdapterFinder;
import com.gorlah.kappabot.function.response.BotResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.gorlah.kappabot.util.StandardContentTypes.PLAIN_TEXT;

@Slf4j
@Component
@RequiredArgsConstructor
public class BotResponseFormatter {

    private final CommandAdapterFinder commandAdapterFinder;
    private final ResponseFormatter responseFormatter;

    public String format(BotRequestMetadata request, BotResponse response) {
        return commandAdapterFinder.findAdapter(request).stream()
                .map(adapter -> format(response, adapter))
                .flatMap(Optional::stream)
                .findFirst()
                .orElse(response.getMessage());
    }

    private Optional<String> format(BotResponse response, CommandAdapter adapter) {
        return Optionals.firstNonEmpty(
                () -> responseFormatter.format(response, adapter.getContentType()),
                () -> {
                    log.warn(
                            "Unable to convert to content type {} for adapter {}.",
                            adapter.getContentType(),
                            adapter.getSource());
                    return responseFormatter.format(response, PLAIN_TEXT);
                });
    }
}
