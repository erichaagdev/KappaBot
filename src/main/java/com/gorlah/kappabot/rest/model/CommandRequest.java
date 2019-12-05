package com.gorlah.kappabot.rest.model;

import com.gorlah.kappabot.function.BotRequestMetadata;
import lombok.Value;

@Value
public class CommandRequest implements BotRequestMetadata {

    private final String author;
    private final String message;

    @Override
    public String getAuthorMention() {
        return author;
    }
}
