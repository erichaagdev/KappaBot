package com.gorlah.kappabot.integration.discord;

import com.google.common.base.Strings;
import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.command.CommandProcessor;
import com.gorlah.kappabot.function.SlashdotParser;
import lombok.RequiredArgsConstructor;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DiscordBot extends ListenerAdapter {

    @Value("${discord.command.prefix}")
    private String commandPrefix;

    @Value("${discord.bot.status}")
    private String botStatus;

    private final CommandProcessor commandProcessor;
    private final DiscordIntegration discordIntegration;

    private JDA bot;

    @PostConstruct
    private void initialize()
            throws LoginException {
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

        String message = event.getMessage().getContentRaw();
        StringBuilder response = new StringBuilder();

        if (SlashdotParser.find(message)) {
            ArrayList<String> titles = SlashdotParser.getTitles(message);

            for (int i = 0; i < titles.size(); i++) {
                if (i > 0) {
                    response.append("\n");
                }

                response.append("> ").append(titles.get(i));
            }
        } else {
            if (message.length() < commandPrefix.length()
                    || !commandPrefix.equalsIgnoreCase(message.substring(0, commandPrefix.length()))) {
                return;
            }

            Command command = new Command(commandPrefix, message, event.getAuthor().getName());

            response = new StringBuilder(commandProcessor.process(command));
            response = new StringBuilder(formatResponse(response.toString(), event));
        }

        if (response.length() > 0) {
            event.getChannel()
                    .sendMessage(response.toString())
                    .queue();
        }
    }

    private String formatResponse(String response, MessageReceivedEvent event) {
        Map<String, String> values = new HashMap<>();

        values.put("user.name", event.getMessage().getAuthor().getName());
        values.put("user.mention", event.getMessage().getAuthor().getAsMention());

        return StringSubstitutor.replace(response, values);
    }
}
