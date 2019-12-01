package com.gorlah.kappabot.subcommand.root.meme.util.creator;

import com.gorlah.kappabot.meme.MemeTemplate;

import java.util.List;

public interface MemeCreator {

    String getName();

    MemeTemplate create(List<String> parameters) throws MemeCreationException;
}
