package com.gorlah.kappabot.meme.template;

import com.gorlah.kappabot.meme.MemeTemplate;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

public class ButterflyMeme extends MemeTemplate {

    @Override
    public String getName() {
        return "butterfly";
    }

    @Override
    public String getFilename() {
        return "butterfly.png";
    }

    public ButterflyMeme(String text) {
        writeText(text, new Font("Arial", Font.ITALIC, 48), Color.WHITE, 10, 675, 1098, 150);
    }

    public ButterflyMeme(String memeText, BufferedImage overlay) {
        this(memeText);

        drawImage(overlay, 650, 75, 450, 400);
    }
}
