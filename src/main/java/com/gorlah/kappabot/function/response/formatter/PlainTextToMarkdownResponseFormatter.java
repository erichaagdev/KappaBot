package com.gorlah.kappabot.function.response.formatter;

import com.gorlah.kappabot.function.response.BotResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

import static com.gorlah.kappabot.util.StandardContentTypes.MARKDOWN;
import static com.gorlah.kappabot.util.StandardContentTypes.PLAIN_TEXT;

@Component
class PlainTextToMarkdownResponseFormatter implements ResponseFormatter {

    @Override
    public Optional<String> format(BotResponse response, String contentType) {
        return Stream.of(response)
                .filter(resp -> PLAIN_TEXT.equals(resp.getContentType()))
                .filter(resp -> MARKDOWN.equals(contentType))
                .map(BotResponse::getMessage)
                .findFirst();
    }
}
