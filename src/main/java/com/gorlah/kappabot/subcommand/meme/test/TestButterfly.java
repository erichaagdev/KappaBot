package com.gorlah.kappabot.subcommand.meme.test;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.meme.template.ButterflyMeme;
import com.gorlah.kappabot.subcommand.Subcommand;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

@Component
public class TestButterfly extends Subcommand {
    
    @Override
    public String getName() {
        return "butterfly";
    }
    
    @Override
    public String getHelpText() {
        return "A humanoid character erroneously identifying a butterfly as a pigeon.";
    }
    
    @Override
    public boolean isShownInHelp() {
        return true;
    }
    
    @Override
    public String process(Command command, ArrayList<String> parameters) throws Exception {
        if (parameters.isEmpty()) {
            return "I need some text to write on the image.";
        }
    
        String memeText = String.join(" ", parameters);
        ButterflyMeme butterflyMeme = new ButterflyMeme(memeText);
        File outputfile = new File("saved.png");
        
        ImageIO.write(butterflyMeme.getImage(), "png", outputfile);
    
        return "Image successfully written to local file.";
    }
}
