package com.gorlah.kappabot.subcommand.meme.test;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.meme.template.ButtonSmashMeme;
import com.gorlah.kappabot.subcommand.Subcommand;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

@Component
public class TestButtonSmash extends Subcommand {
    
    @Override
    public String getName() {
        return "buttonSmash";
    }
    
    @Override
    public String getHelpText() {
        return "An image of a hand about to hit a blue button.";
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
        ButtonSmashMeme buttonSmashMeme = new ButtonSmashMeme(memeText);
        File outputfile = new File("saved.png");
    
        ImageIO.write(buttonSmashMeme.getImage(), "png", outputfile);
        
        return "Image successfully written to local file.";
    }
}
