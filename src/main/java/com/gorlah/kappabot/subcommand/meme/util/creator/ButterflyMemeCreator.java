package com.gorlah.kappabot.subcommand.meme.util.creator;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.meme.MemeTemplate;
import com.gorlah.kappabot.meme.template.ButterflyMeme;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@Component
public class ButterflyMemeCreator implements MemeCreator {

    @Override
    public String getName() {
        return "butterfly";
    }

    @Override
    public MemeTemplate create(Command command, ArrayList<String> parameters) throws MemeCreationException {
        String memeText;
        URL overlayURL = null;
        BufferedImage overlay = null;

        try {
            overlayURL = new URL(parameters.get(parameters.size() - 1));
        } catch (MalformedURLException ignored) {

        }

        if (overlayURL == null) {
            memeText = String.join(" ", parameters);
        } else {
            memeText = String.join(" ", parameters.subList(0, parameters.size() - 1));

            try {
                overlay = ImageIO.read(overlayURL);
            } catch (IOException ignored) {

            }
        }

        if (overlay == null && memeText.isEmpty()) {
            throw new MemeCreationException("Both the meme text and image can't be blank.");
        } else if (overlay == null) {
            return new ButterflyMeme(memeText);
        } else {
            return new ButterflyMeme(memeText, overlay);
        }
    }
}
