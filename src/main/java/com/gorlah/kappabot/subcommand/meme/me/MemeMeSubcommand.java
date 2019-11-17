package com.gorlah.kappabot.subcommand.meme.me;

import com.gorlah.kappabot.subcommand.Subcommand;
import com.gorlah.kappabot.subcommand.meme.MemeMe;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class MemeMeSubcommand extends Subcommand {

    @Override
    public Class<? extends Subcommand> getParent() {
        return MemeMe.class;
    }
    
    boolean isTest(ArrayList<String> parameters) {
        if (!parameters.isEmpty() && "--test".equalsIgnoreCase(parameters.get(0))) {
            parameters.remove(0);
            return true;
        }
        
        return false;
    }
    
    void writeToLocalFile(BufferedImage image) throws IOException {
        File outputfile = new File("saved.png");
        ImageIO.write(image, "png", outputfile);
    }
}
