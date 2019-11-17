package com.gorlah.kappabot.subcommand.meme.test;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.subcommand.Subcommand;
import com.gorlah.kappabot.subcommand.meme.MemeSubcommand;
import com.gorlah.kappabot.subcommand.meme.MemeTest;
import com.gorlah.kappabot.subcommand.meme.util.MemeManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
    public Class<? extends Subcommand> getParent() {
        return MemeTest.class;
    }

    @Override
    protected String doProcess(Command command, ArrayList<String> parameters) throws Exception {
        memeManager.getOrCreate("buttonSmash", "file", command, parameters, false);
        return "Image successfully written to local file.";
    }
}
