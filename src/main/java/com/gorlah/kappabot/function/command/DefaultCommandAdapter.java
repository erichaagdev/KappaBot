package com.gorlah.kappabot.function.command;

import com.gorlah.kappabot.util.StandardContentTypes;
import org.springframework.stereotype.Component;

@Component
public class DefaultCommandAdapter implements CommandAdapter {

    public static final String DEFAULT_COMMAND_PREFIX = "/kb";
    public static final String DEFAULT_SOURCE = "default";

    @Override
    public String getCommandPrefix() {
        return DEFAULT_COMMAND_PREFIX;
    }

    @Override
    public String getSource() {
        return DEFAULT_SOURCE;
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
