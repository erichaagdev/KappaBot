package com.gorlah.kappabot.integration.discord;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.gorlah.kappabot.function.BotRequestMetadata;
import com.gorlah.kappabot.function.FunctionProcessor;
import com.gorlah.kappabot.integration.ImmutableBotRequestMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiscordBot extends ListenerAdapter {

    @Value("${discord.command.prefix}")
    private String commandPrefix;

    @Value("${discord.bot.status}")
    private String botStatus;

    private final DiscordIntegration discordIntegration;
    private final FunctionProcessor functionProcessor;

    private JDA bot;

    @PostConstruct
    private void initialize() throws LoginException {
        if (discordIntegration.isEnabled()) {
            bot = new JDABuilder(discordIntegration.getToken())
                    .addEventListeners(this)
                    .setActivity(Activity.playing(Strings.isNullOrEmpty(botStatus) ? commandPrefix + " help" : botStatus))
                    .build();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        processMessageEvent(event).forEach(message -> sendResponse(message, event));
    }

    private void sendResponse(String message, MessageReceivedEvent event) {
        event.getChannel().sendMessage(message).queue();
    }

    private List<String> processMessageEvent(MessageReceivedEvent event) {
        return formatResponses(functionProcessor.process(extractMetadata(event)), event);
    }

    private BotRequestMetadata extractMetadata(MessageReceivedEvent event) {
        return ImmutableBotRequestMetadata.builder()
                .author(event.getAuthor().getName())
                .message(event.getMessage().getContentRaw())
                .source(DiscordIntegration.SOURCE)
                .build();
    }

    private List<String> formatResponses(List<String> responses, MessageReceivedEvent event) {
        return responses.stream()
                .map(response -> formatResponse(response, event))
                .collect(ImmutableList.toImmutableList());
    }

    private String formatResponse(String response, MessageReceivedEvent event) {
        Map<String, String> values = new HashMap<>();

        values.put("user.name", event.getMessage().getAuthor().getName());
        values.put("user.mention", event.getMessage().getAuthor().getAsMention());

        return StringSubstitutor.replace(response, values);
    }
}
