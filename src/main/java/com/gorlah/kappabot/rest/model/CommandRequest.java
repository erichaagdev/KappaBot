package com.gorlah.kappabot.rest.model;

import com.gorlah.kappabot.function.BotRequestMetadata;
import lombok.Value;

@Value
public class CommandRequest implements BotRequestMetadata {

    private String author;
    private String message;
    private String source;
}
