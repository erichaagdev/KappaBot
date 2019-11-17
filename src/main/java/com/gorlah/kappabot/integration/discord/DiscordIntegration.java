package com.gorlah.kappabot.integration.discord;

import com.google.common.base.Strings;
import com.gorlah.kappabot.integration.KappaBotIntegration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DiscordIntegration implements KappaBotIntegration {

    @Value("${discord.token}")
    private String discordToken;

    @Override
    public String getName() {
        return "Discord";
    }

    @Override
    public boolean isEnabled() {
        return !Strings.isNullOrEmpty(discordToken);
    }

    @Override
    public void initialize() {
        if (!isEnabled()) {
            log.warn("Discord integration disabled! ('discord.token' property is not set)");
        }
    }

    public String getToken() {
        return discordToken;
    }
}
