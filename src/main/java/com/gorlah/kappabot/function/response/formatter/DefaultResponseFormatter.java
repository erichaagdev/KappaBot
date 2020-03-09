package com.gorlah.kappabot.function.response.formatter;

import com.gorlah.kappabot.function.response.BotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.inject.Provider;
import java.util.Optional;
import java.util.Set;

@Primary
@Component
@RequiredArgsConstructor
class DefaultResponseFormatter implements ResponseFormatter {

    private final Provider<Set<ResponseFormatter>> responseFormatters;

    @Override
    public Optional<String> format(BotResponse response, String contentType) {
        return responseFormatters.get().stream()
                .filter(formatter -> formatter != this)
                .map(formatter -> formatter.format(response, contentType))
                .flatMap(Optional::stream)
                .findFirst();
    }
}
