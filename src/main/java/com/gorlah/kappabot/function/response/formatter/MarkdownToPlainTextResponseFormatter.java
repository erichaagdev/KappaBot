package com.gorlah.kappabot.function.response.formatter;

import com.google.common.base.Splitter;
import com.gorlah.kappabot.function.response.BotResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class MarkdownToPlainTextResponseFormatter implements ResponseFormatter {

    @Override
    public Optional<String> format(BotResponse response, String contentType) {
        return Stream.of(response)
                .filter(resp -> resp.getContentType().equals("text/markdown"))
                .filter(resp -> contentType.equals("text/plain"))
                .map(resp -> convertToPlainText(resp.getMessage()))
                .findFirst();
    }

    private String convertToPlainText(String markdown) {
        return Splitter.on('\n').splitToStream(
                markdown.replaceAll("`", "")
                        .replaceAll("\\*", "")
                        .replaceAll("_", "")
                        .replaceAll("#", ""))
                .map(String::trim)
                .filter(line -> !line.isBlank())
                .filter(line -> !line.replaceAll("-", "").isBlank())
                .collect(Collectors.joining("\n"));
    }
}
