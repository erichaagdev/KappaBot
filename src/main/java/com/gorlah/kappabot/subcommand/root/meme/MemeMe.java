package com.gorlah.kappabot.subcommand.root.meme;

import com.gorlah.kappabot.integration.imgur.ImgurIntegration;
import com.gorlah.kappabot.subcommand.SkeletalKappaBotCommand;
import com.gorlah.kappabot.subcommand.root.MemeCommand;
import com.gorlah.kappabot.command.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemeMe extends SkeletalKappaBotCommand {

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
    public Class<? extends Command> getParent() {
        return MemeCommand.class;
    }

    @Override
    public boolean isEnabled() {
        return imgurIntegration.isEnabled();
    }
}