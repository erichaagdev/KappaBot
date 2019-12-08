package com.gorlah.kappabot.function;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BotRequestEndpoint {
    DISCORD("Discord"),
    REST("Rest");

    @Getter private final String endpointName;
}
