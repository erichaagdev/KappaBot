package com.gorlah.kappabot.function;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class BotResponseFormatter {

    public String format(String response, BotRequestMetadata metadata) {
        return StringSubstitutor.replace(response, Map.of("authorMention", metadata.getAuthorMention()));
    }
}
