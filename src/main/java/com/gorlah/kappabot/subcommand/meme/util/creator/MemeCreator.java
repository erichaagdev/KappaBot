package com.gorlah.kappabot.subcommand.meme.util.creator;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.meme.MemeTemplate;

import java.util.ArrayList;

public interface MemeCreator {

    String getName();

    MemeTemplate create(Command command, ArrayList<String> parameters) throws MemeCreationException;
}
