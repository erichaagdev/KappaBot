package com.gorlah.kappabot.function.response.formatter;

import com.gorlah.kappabot.function.response.BotResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A simple formatter for messages that are already in the correct format.
 */
@Component
class SimpleResponseFormatter implements ResponseFormatter {

    @Override
    public Optional<String> format(BotResponse response, String contentType) {
        return Optional.of(response)
                .filter(resp -> resp.getContentType().equals(contentType))
                .map(BotResponse::getMessage);
    }
}
