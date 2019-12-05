package com.gorlah.kappabot.integration.discord;

import com.gorlah.kappabot.function.BotRequestMetadata;
import lombok.Value;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Value
public class DiscordRequestMetadata implements BotRequestMetadata {

    private final String author;
    private final String authorMention;
    private final String message;

    DiscordRequestMetadata(MessageReceivedEvent event) {
        this.author = event.getAuthor().getName();
        this.authorMention = event.getAuthor().getAsMention();
        this.message = event.getMessage().getContentRaw();
    }
}
