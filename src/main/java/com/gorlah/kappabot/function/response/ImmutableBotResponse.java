package com.gorlah.kappabot.function.response;

import lombok.Value;

@Value(staticConstructor = "of")
public class ImmutableBotResponse implements BotResponse {

    String contentType;
    String message;
}
