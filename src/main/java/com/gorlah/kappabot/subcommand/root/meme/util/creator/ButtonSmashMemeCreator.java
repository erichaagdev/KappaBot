package com.gorlah.kappabot.subcommand.root.meme.util.creator;

import com.gorlah.kappabot.meme.ButtonSmashMeme;
import com.gorlah.kappabot.meme.MemeTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ButtonSmashMemeCreator implements MemeCreator {

    @Override
    public String getName() {
        return "buttonSmash";
    }

    @Override
    public MemeTemplate create(List<String> parameters) {
        String memeText = String.join(" ", parameters);

        return new ButtonSmashMeme(memeText);
    }
}
