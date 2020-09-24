package com.gorlah.kappabot.subcommand.root.meme.util.writer;

import com.gorlah.kappabot.integration.imgur.ImgurImage;
import com.gorlah.kappabot.integration.imgur.ImgurImageUpload;
import com.gorlah.kappabot.integration.imgur.ImgurIntegration;
import com.gorlah.kappabot.meme.Meme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemeImgurWriter implements MemeWriter {

    private final ImgurIntegration imgurIntegration;
    private final ImgurImageUpload imgurImageUpload;

    @Override
    public String getName() {
        return "imgur";
    }

    @Override
    public Optional<String> write(Meme meme) {
        imgurIntegration.require();
        ImgurImage imgurImage = new ImgurImage(meme.asImage());
        return imgurImageUpload.upload(imgurImage);
    }
}
