package com.gorlah.kappabot.subcommand.root.meme.util.writer;

import com.gorlah.kappabot.meme.Meme;

import java.util.Optional;

public interface MemeWriter {

    String getName();

    /**
     * @return the location of the meme
     */
    Optional<String> write(Meme memeBuilderPipeline);
}
