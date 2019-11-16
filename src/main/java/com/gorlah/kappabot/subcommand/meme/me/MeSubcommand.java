package com.gorlah.kappabot.subcommand.meme.me;

import com.gorlah.kappabot.subcommand.Subcommand;
import com.gorlah.kappabot.imgur.ImgurImageUpload;
import com.gorlah.kappabot.jpa.repository.MemeRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class MeSubcommand extends Subcommand {
    
    MemeRepository memeRepository;
    ImgurImageUpload imgurImageUpload;
    
    public MeSubcommand(MemeRepository memeRepository,
                        ImgurImageUpload imgurImageUpload) {
        this.memeRepository = memeRepository;
        this.imgurImageUpload = imgurImageUpload;
    }
}
