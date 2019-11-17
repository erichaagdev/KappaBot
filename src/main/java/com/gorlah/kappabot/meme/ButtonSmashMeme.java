package com.gorlah.kappabot.meme;

import java.awt.Color;

public class ButtonSmashMeme extends MemeTemplate {

    @Override
    public String getName() {
        return "buttonSmash";
    }

    @Override
    public String getFilename() {
        return "buttonSmash.png";
    }

    public ButtonSmashMeme(String text) {
        writeText(text, Color.WHITE, 15, 200, 300, 200);
    }
}
