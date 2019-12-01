package com.gorlah.kappabot.subcommand.root.meme.util.writer;

import com.gorlah.kappabot.meme.MemeTemplate;

public interface MemeWriter {

    String getName();

    /**
     * @return the location of the meme
     */
    String write(MemeTemplate memeTemplate) throws Exception;
}
