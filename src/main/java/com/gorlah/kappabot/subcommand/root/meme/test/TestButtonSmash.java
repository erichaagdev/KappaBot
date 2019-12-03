package com.gorlah.kappabot.subcommand.root.meme.test;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.command.CommandPayload;
import com.gorlah.kappabot.subcommand.root.meme.MemeSubcommand;
import com.gorlah.kappabot.subcommand.root.meme.MemeTest;
import com.gorlah.kappabot.subcommand.root.meme.util.MemeManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestButtonSmash extends MemeSubcommand {

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
        return MemeTest.class;
    }

    @Override
    protected String doProcess(CommandPayload payload) throws Exception {
        memeManager.getOrCreate("buttonSmash", "file", payload, false);
        return "Image successfully written to local file.";
    }
}
