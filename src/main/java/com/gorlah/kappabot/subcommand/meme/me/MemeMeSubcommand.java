package com.gorlah.kappabot.subcommand.meme.me;

import com.gorlah.kappabot.subcommand.Subcommand;
import com.gorlah.kappabot.subcommand.meme.MemeMe;

public abstract class MemeMeSubcommand extends Subcommand {

    @Override
    public Class<? extends Subcommand> getParent() {
        return MemeMe.class;
    }
}
