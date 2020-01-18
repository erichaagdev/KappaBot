package com.gorlah.kappabot.function;

import java.util.Optional;

public interface BotFunction {

    /**
     * Performs processing on the specified request if possible.
     *
     * @param metadata metadata related to the bot request
     * @return the response based on the message
     */
    Optional<String> process(BotRequestMetadata metadata);
}
