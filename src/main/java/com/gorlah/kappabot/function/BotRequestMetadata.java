package com.gorlah.kappabot.function;

public interface BotRequestMetadata {

    String getAuthor();
    String getAuthorMention();
    BotRequestEndpoint getEndpoint();
    String getMessage();
}
