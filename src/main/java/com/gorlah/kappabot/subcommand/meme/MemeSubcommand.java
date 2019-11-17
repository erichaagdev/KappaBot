package com.gorlah.kappabot.subcommand.meme;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.subcommand.Subcommand;
import com.gorlah.kappabot.subcommand.meme.util.creator.MemeCreationException;

import java.util.ArrayList;

public abstract class MemeSubcommand extends Subcommand {

    @Override
    public boolean isShownInHelp() {
        return true;
    }

    @Override
    public String process(Command command, ArrayList<String> parameters)
            throws Exception {
        if (parameters.isEmpty()) {
            return "I need some text to write on the image.";
        }
        try {
            return doProcess(command, parameters);
        } catch (MemeCreationException e) {
            return e.getMessage();
        }
    }

    protected abstract String doProcess(Command command, ArrayList<String> parameters)
            throws Exception;
}
