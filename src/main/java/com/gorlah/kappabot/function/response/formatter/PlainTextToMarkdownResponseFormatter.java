package com.gorlah.kappabot.function.response.formatter;

import com.gorlah.kappabot.function.response.BotResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

@Component
class PlainTextToMarkdownResponseFormatter implements ResponseFormatter {

    @Override
    public Optional<String> format(BotResponse response, String contentType) {
        return Stream.of(response)
                .filter(resp -> resp.getContentType().equals("text/plain"))
                .filter(resp -> contentType.equals("text/markdown"))
                .map(BotResponse::getMessage)
                .findFirst();
    }
}
