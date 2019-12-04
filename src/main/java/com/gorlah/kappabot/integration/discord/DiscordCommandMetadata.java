package com.gorlah.kappabot.integration.discord;

import com.gorlah.kappabot.command.CommandMetadata;
import lombok.Value;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Value
public class DiscordCommandMetadata implements CommandMetadata {

    private final String author;
    private final String message;

    DiscordCommandMetadata(MessageReceivedEvent event) {
        this.author = event.getAuthor().getName();
        this.message = event.getMessage().getContentRaw();
    }
}
