package com.gorlah.kappabot.integration.discord;

import com.gorlah.kappabot.function.command.CommandAdapter;
import com.gorlah.kappabot.util.StandardContentTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscordCommandAdapter implements CommandAdapter {

    private final DiscordIntegration discordIntegration;

    @Override
    public String getCommandPrefix() {
        return discordIntegration.getCommandPrefix();
    }

    @Override
    public String getSource() {
        return DiscordIntegration.SOURCE;
    }

    @Override
    public String getContentType() {
        return StandardContentTypes.MARKDOWN;
    }

    @Override
    public boolean isEnabled() {
        return discordIntegration.isEnabled();
    }
}
