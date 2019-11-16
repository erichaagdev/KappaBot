package com.gorlah.kappabot.meme.template;

import com.gorlah.kappabot.meme.MemeTemplate;

import java.awt.*;
import java.io.IOException;

public class ButterflyMeme extends MemeTemplate {
    
    @Override
    public String getFilename() {
        return "butterfly.png";
    }
    
    public ButterflyMeme(String text) throws IOException {
        super();
        
        writeText(text, new Font("Arial", Font.ITALIC, 48), Color.WHITE, 10, 675, 1098, 150);
    }
}
