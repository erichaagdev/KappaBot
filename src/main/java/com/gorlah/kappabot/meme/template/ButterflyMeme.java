package com.gorlah.kappabot.meme.template;

import com.gorlah.kappabot.meme.MemeTemplate;

import java.awt.*;
import java.awt.image.BufferedImage;
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
    
    public ButterflyMeme(String memeText, BufferedImage overlay) throws IOException {
        this(memeText);
        
        drawImage(overlay, 650, 75, 450, 400);
    }
}
