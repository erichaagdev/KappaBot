package com.gorlah.kappabot.subcommand.meme.me;

import com.gorlah.kappabot.command.Subcommand;
import com.gorlah.kappabot.imgur.ImgurImageUpload;
import com.gorlah.kappabot.jpa.repository.MemeRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class MemeSubcommand extends Subcommand {
    
    protected MemeRepository memeRepository;
    protected ImgurImageUpload imgurImageUpload;
    
    public MemeSubcommand(MemeRepository memeRepository,
                          ImgurImageUpload imgurImageUpload) {
        this.memeRepository = memeRepository;
        this.imgurImageUpload = imgurImageUpload;
    }
}
