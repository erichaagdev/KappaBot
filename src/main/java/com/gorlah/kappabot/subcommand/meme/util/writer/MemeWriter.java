package com.gorlah.kappabot.subcommand.meme.util.writer;

import com.gorlah.kappabot.meme.MemeTemplate;

public interface MemeWriter {

    String getName();

    /**
     * @return the url of the meme
     */
    String write(MemeTemplate memeTemplate)
            throws Exception;
}
