package com.gorlah.kappabot.function;

import com.gorlah.kappabot.function.response.BotResponse;

import java.util.Optional;

public interface BotFunction {

    /**
     * Performs processing on the specified request if possible.
     *
     * @param metadata metadata related to the bot request
     * @return the response based on the message
     */
    Optional<BotResponse> process(BotRequestMetadata metadata);
}
