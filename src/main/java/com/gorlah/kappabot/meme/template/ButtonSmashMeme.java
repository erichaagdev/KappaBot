package com.gorlah.kappabot.meme.template;

import com.gorlah.kappabot.meme.MemeTemplate;

import java.awt.*;
import java.io.IOException;

public class ButtonSmashMeme extends MemeTemplate {
    
    @Override
    public String getFilename() {
        return "buttonSmash.png";
    }
    
    public ButtonSmashMeme(String text) throws IOException{
        super();
    
        writeText(text, Color.WHITE, 15, 200, 300, 200);
    }
}
