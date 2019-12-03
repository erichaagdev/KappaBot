package com.gorlah.kappabot.integration.discord;

import com.google.common.base.Strings;
import com.gorlah.kappabot.function.FunctionProcessor;
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

        long start = System.currentTimeMillis();

        event.getChannel().sendMessage(processMessageEvent(event)).queue();

        long end = System.currentTimeMillis();

        log.info("Call returned in " + (end - start) + " ms");
    }

    private String processMessageEvent(MessageReceivedEvent event) {
        return formatResponse(functionProcessor.process(new DiscordRequestMetadata(event)), event);
    }

    private String formatResponse(String response, MessageReceivedEvent event) {
        Map<String, String> values = new HashMap<>();

        values.put("user.name", event.getMessage().getAuthor().getName());
        values.put("user.mention", event.getMessage().getAuthor().getAsMention());

        return StringSubstitutor.replace(response, values);
    }
}
