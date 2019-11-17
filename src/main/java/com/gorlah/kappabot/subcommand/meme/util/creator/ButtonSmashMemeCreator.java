package com.gorlah.kappabot.subcommand.meme.util.creator;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.meme.MemeTemplate;
import com.gorlah.kappabot.meme.template.ButtonSmashMeme;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author ploober
 */
@Component
public class ButtonSmashMemeCreator implements MemeCreator {

    @Override
    public String getName() {
        return "buttonSmash";
    }

    @Override
    public MemeTemplate create(Command command, ArrayList<String> parameters) {
        String memeText = String.join(" ", parameters);
        return new ButtonSmashMeme(memeText);
    }
}
