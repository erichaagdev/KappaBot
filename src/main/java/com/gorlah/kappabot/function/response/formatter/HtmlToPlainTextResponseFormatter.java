package com.gorlah.kappabot.function.response.formatter;

import com.gorlah.kappabot.function.response.BotResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

import static com.gorlah.kappabot.util.StandardContentTypes.HTML;
import static com.gorlah.kappabot.util.StandardContentTypes.PLAIN_TEXT;

@Component
class HtmlToPlainTextResponseFormatter implements ResponseFormatter {

    @Override
    public Optional<String> format(BotResponse response, String contentType) {
        return Stream.of(response)
                .filter(resp -> HTML.equals(resp.getContentType()))
                .filter(resp -> PLAIN_TEXT.equals(contentType))
                .map(BotResponse::getMessage)
                .map(Jsoup::parse)
                .map(Document::text)
                .findFirst();
    }
}
