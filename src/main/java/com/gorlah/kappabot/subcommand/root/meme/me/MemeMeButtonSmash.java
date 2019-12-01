package com.gorlah.kappabot.subcommand.root.meme.me;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.command.CommandPayload;
import com.gorlah.kappabot.subcommand.root.meme.MemeMe;
import com.gorlah.kappabot.subcommand.root.meme.MemeSubcommand;
import com.gorlah.kappabot.subcommand.root.meme.util.MemeManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemeMeButtonSmash extends MemeSubcommand {

    private final MemeManager memeManager;

    @Override
    public String getName() {
        return "buttonSmash";
    }

    @Override
    public String getHelpText() {
        return "An image of a hand about to hit a blue button.";
    }

    @Override
    public Class<? extends Command> getParent() {
        return MemeMe.class;
    }

    @Override
    protected String doProcess(CommandPayload payload) throws Exception {
        return memeManager.getOrCreate("buttonSmash", "imgur", payload, true);
    }
}
