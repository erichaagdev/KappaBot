package com.gorlah.kappabot.integration.discord;

import com.google.common.base.Strings;
import com.gorlah.kappabot.integration.KappaBotIntegration;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Component
public class DiscordIntegration implements KappaBotIntegration {

    public static final String SOURCE = "discord";

    private final String name = "Discord";

    @Value("${discord.command.prefix}")
    private String commandPrefix;

    @Value("${discord.token}")
    private String token;

    @Override
    public boolean isEnabled() {
        return !Strings.isNullOrEmpty(token);
    }

    @Override
    public void initialize() {
        if (!isEnabled()) {
            log.warn("Discord integration disabled! ('discord.token' property is not set)");
        }
    }
}
