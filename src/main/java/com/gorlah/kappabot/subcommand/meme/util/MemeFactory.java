package com.gorlah.kappabot.subcommand.meme.util;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.meme.MemeTemplate;
import com.gorlah.kappabot.subcommand.meme.util.creator.MemeCreationException;
import com.gorlah.kappabot.subcommand.meme.util.creator.MemeCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;

@Component
@RequiredArgsConstructor
class MemeFactory {

    private final Set<MemeCreator> memeCreators;

    MemeTemplate create(String memeName, Command command, ArrayList<String> parameters) throws MemeCreationException {
        return getCreatorForMeme(memeName).create(command, parameters);
    }

    private MemeCreator getCreatorForMeme(String memeName) throws MemeCreationException {
        return memeCreators.stream()
                .filter(memeCreator -> memeCreator.getName().equalsIgnoreCase(memeName))
                .findFirst()
                .orElseThrow(() -> new MemeCreationException("Unknown meme: " + memeName));
    }
}
