package com.gorlah.kappabot.subcommand.meme.me;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.imgur.ImgurImage;
import com.gorlah.kappabot.imgur.ImgurImageUpload;
import com.gorlah.kappabot.jpa.entity.Meme;
import com.gorlah.kappabot.jpa.repository.MemeRepository;
import com.gorlah.kappabot.meme.template.ButterflyMeme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        if (parameters.isEmpty()) {
            return "I need some text to write on the image.";
        }
    
        String memeText = String.join(" ", parameters);
    
        Meme meme = memeRepository.findByMemeNameAndParameters(getName(), memeText);
    
        if (meme != null) {
            meme.incrementUseCount();
            memeRepository.save(meme);
        
            return meme.getUrl();
        }
    
        ButterflyMeme butterflyMeme = new ButterflyMeme(memeText);
        ImgurImage imgurImage = new ImgurImage(butterflyMeme.getImage());
        
        String imgurUrl = imgurImageUpload.upload(imgurImage);
    
        meme = Meme.builder()
                .memeName(getName())
                .parameters(memeText)
                .url(imgurUrl)
                .user(command.getCalledBy())
                .build();
        
        meme = memeRepository.save(meme);
        
        return meme.getUrl();
    }
}
