package com.gorlah.kappabot.function.command;

import com.gorlah.kappabot.util.StandardContentTypes;
import org.springframework.stereotype.Component;

@Component
public class DefaultCommandAdapter implements CommandAdapter {

    @Override
    public String getCommandPrefix() {
        return "/kb";
    }

    @Override
    public String getSource() {
        return "default";
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
