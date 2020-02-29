package com.gorlah.kappabot.function.response.formatter;

import com.gorlah.kappabot.function.response.BotResponse;

import java.util.Optional;


public interface ResponseFormatter {

    /**
     * Attempts to format a bot's response to the specified content type.
     *
     * @param response a response from the bot
     * @param contentType the desired output content type
     * @return the formatted message if the formatter was able to convert it
     */
    Optional<String> format(BotResponse response, String contentType);
}
