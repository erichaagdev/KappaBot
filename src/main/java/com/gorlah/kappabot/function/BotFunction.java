package com.gorlah.kappabot.function;

import com.gorlah.kappabot.command.CommandMetadata;

public interface BotFunction {

    /**
     * Takes in a message and returns whether or not it meets a specified condition.
     *
     * @param message the message to check
     * @return whether or not the message meets a specified condition
     */
    boolean shouldProcess(String message);

    /**
     * The method to be activated based on the above condition.
     *
     * @param metadata metadata related to the bot request
     * @return the response based on the message
     */
    String process(CommandMetadata metadata);
}
