package com.gorlah.kappabot.subcommand.meme.me;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.subcommand.Subcommand;
import com.gorlah.kappabot.subcommand.meme.MemeMe;
import com.gorlah.kappabot.subcommand.meme.MemeSubcommand;
import com.gorlah.kappabot.subcommand.meme.util.MemeManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class MemeMeButterfly extends MemeSubcommand {

    private final MemeManager memeManager;

    @Override
    public String getName() {
        return "butterfly";
    }

    @Override
    public String getHelpText() {
        return "A humanoid character erroneously identifying a butterfly as a pigeon.";
    }

    @Override
    public Class<? extends Subcommand> getParent() {
        return MemeMe.class;
    }

    @Override
    protected String doProcess(Command command, ArrayList<String> parameters) throws Exception {
        return memeManager.getOrCreate("butterfly", "imgur", command, parameters, true);
    }
}
