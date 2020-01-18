package com.gorlah.kappabot.integration;

import com.gorlah.kappabot.function.BotRequestMetadata;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class ImmutableBotRequestMetadata implements BotRequestMetadata {

    private final String author;
    private final String message;
    private final String source;
}
