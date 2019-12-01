package com.gorlah.kappabot.subcommand.root.meme.util;

import com.gorlah.kappabot.meme.MemeTemplate;
import com.gorlah.kappabot.subcommand.root.meme.util.creator.MemeCreationException;
import com.gorlah.kappabot.subcommand.root.meme.util.creator.MemeCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
class MemeFactory {

    private final Set<MemeCreator> memeCreators;

    MemeTemplate create(String memeName, List<String> parameters) throws MemeCreationException {
        return getCreatorForMeme(memeName).create(parameters);
    }

    private MemeCreator getCreatorForMeme(String memeName) throws MemeCreationException {
        return memeCreators.stream()
                .filter(memeCreator -> memeCreator.getName().equalsIgnoreCase(memeName))
                .findFirst()
                .orElseThrow(() -> new MemeCreationException("Unknown meme: " + memeName));
    }
}
