package com.gorlah.kappabot.function;

import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ResponseMetadataReplacer {

    public String replace(String response, BotRequestMetadata metadata) {
        return StringSubstitutor.replace(response, Map.of("authorMention", metadata.getAuthorMention()));
    }
}
