package com.gorlah.kappabot.subcommand.meme.me;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.imgur.ImgurImage;
import com.gorlah.kappabot.imgur.ImgurImageUpload;
import com.gorlah.kappabot.jpa.entity.Meme;
import com.gorlah.kappabot.jpa.repository.MemeRepository;
import com.gorlah.kappabot.meme.template.ButterflyMeme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class MemeMeButterfly extends MemeMeSubcommand {

    private final MemeRepository memeRepository;
    private final ImgurImageUpload imgurImageUpload;
    
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
        boolean test = isTest(parameters);
        
        if (parameters.isEmpty()) {
            return "I need some text to write on the image.";
        }
    
        String memeText;
        ButterflyMeme butterflyMeme;
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
            return "Both the meme text and image can't be blank.";
        } else if (overlay == null) {
            butterflyMeme = new ButterflyMeme(memeText);
        } else {
            butterflyMeme = new ButterflyMeme(memeText, overlay);
        }
        
        if (test) {
            writeToLocalFile(butterflyMeme.getImage());
            return "Image successfully written to local file.";
        } else {
            Meme meme = memeRepository.findByMemeNameAndParameters(getName(), String.join(" ", parameters));
    
            if (meme != null) {
                meme.incrementUseCount();
                memeRepository.save(meme);
        
                return meme.getUrl();
            }
    
            ImgurImage imgurImage = new ImgurImage(butterflyMeme.getImage());
    
            String imgurUrl = imgurImageUpload.upload(imgurImage);
    
            meme = Meme.builder()
                    .memeName(getName())
                    .parameters(String.join(" ", parameters))
                    .url(imgurUrl)
                    .user(command.getCalledBy())
                    .build();
    
            meme = memeRepository.save(meme);
    
            return meme.getUrl();
        }
    }
}
