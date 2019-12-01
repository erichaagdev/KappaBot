package com.gorlah.kappabot.subcommand.root.image;

import com.gorlah.kappabot.command.CommandPayload;
import org.springframework.stereotype.Component;

@Component
public class ImageList extends ImageSubcommand {

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getHelpText() {
        return "Gets a list of images.";
    }

    @Override
    public String process(CommandPayload payload) {
        return "You can see all of my known images here: https://kb.gorlah.com/";
    }
}
