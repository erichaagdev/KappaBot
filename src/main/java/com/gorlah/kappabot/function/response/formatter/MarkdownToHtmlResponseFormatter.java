package com.gorlah.kappabot.function.response.formatter;

import com.gorlah.kappabot.function.response.BotResponse;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

@Component
class MarkdownToHtmlResponseFormatter implements ResponseFormatter {

    private final Parser parser = Parser.builder().build();
    private final HtmlRenderer renderer = HtmlRenderer.builder().build();

    @Override
    public Optional<String> format(BotResponse response, String contentType) {
        return Stream.of(response)
                .filter(resp -> resp.getContentType().equals("text/markdown"))
                .filter(resp -> contentType.equals("text/html"))
                .map(BotResponse::getMessage)
                .map(parser::parse)
                .map(renderer::render)
                .findFirst();
    }
}
