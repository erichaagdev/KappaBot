package com.gorlah.kappabot.subcommand.image;

import com.gorlah.kappabot.command.Command;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
    public String process(Command command, ArrayList<String> parameters) {
        return "You can see all of my known images here: https://kb.gorlah.com/";
    }
}
