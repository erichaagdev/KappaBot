package com.gorlah.kappabot.subcommand.meme.me;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.imgur.ImgurImage;
import com.gorlah.kappabot.imgur.ImgurImageUpload;
import com.gorlah.kappabot.jpa.entity.Meme;
import com.gorlah.kappabot.jpa.repository.MemeRepository;
import com.gorlah.kappabot.meme.template.ButtonSmashMeme;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MeButtonSmash extends MemeSubcommand {
    
    public MeButtonSmash(MemeRepository memeRepository,
                         ImgurImageUpload imgurImageUpload) {
        super(memeRepository, imgurImageUpload);
    }
    
    @Override
    public String getName() {
        return "buttonSmash";
    }
    
    @Override
    public String getHelpText() {
        return "An image of a hand about to hit a blue button.";
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
        
        ButtonSmashMeme buttonSmashMeme = new ButtonSmashMeme(memeText);
        ImgurImage imgurImage = new ImgurImage(buttonSmashMeme.getImage());
    
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
