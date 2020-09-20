package com.gorlah.kappabot.function.command;

import com.gorlah.kappabot.util.StandardContentTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultCommandAdapter implements CommandAdapter {

    @Value("${kappabot.default.command.prefix:}")
    private String defaultCommandPrefix;

    @Override
    public String getCommandPrefix() {
        return defaultCommandPrefix;
    }

    @Override
    public String getSource() {
        return "";
    }

    @Override
    public String getContentType() {
        return StandardContentTypes.PLAIN_TEXT;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
