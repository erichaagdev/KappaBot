package com.gorlah.kappabot.rest.model;

import com.gorlah.kappabot.command.CommandMetadata;
import lombok.Value;

@Value
public class RestCommandMetadata implements CommandMetadata {

    private final String author;
    private final String message;

    public RestCommandMetadata(CommandRequest request) {
        this.author = request.getUser();
        this.message = request.getCommand();
    }
}
