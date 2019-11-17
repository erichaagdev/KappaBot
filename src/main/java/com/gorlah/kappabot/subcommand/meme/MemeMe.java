package com.gorlah.kappabot.subcommand.meme;

import com.gorlah.kappabot.integration.imgur.ImgurIntegration;
import com.gorlah.kappabot.subcommand.MemeCommand;
import com.gorlah.kappabot.subcommand.Subcommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemeMe extends Subcommand {

    private final ImgurIntegration imgurIntegration;

    @Override
    public String getName() {
        return "me";
    }

    @Override
    public String getHelpText() {
        return "Generates a meme.";
    }

    @Override
    public boolean isShownInHelp() {
        return true;
    }

    @Override
    public Class<? extends Subcommand> getParent() {
        return MemeCommand.class;
    }

    @Override
    public boolean isEnabled() {
        return imgurIntegration.isEnabled();
    }
}