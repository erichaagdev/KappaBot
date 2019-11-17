package com.gorlah.kappabot.subcommand.meme.util.writer;

import com.gorlah.kappabot.integration.imgur.ImgurImage;
import com.gorlah.kappabot.integration.imgur.ImgurImageUpload;
import com.gorlah.kappabot.integration.imgur.ImgurIntegration;
import com.gorlah.kappabot.meme.MemeTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImgurMemeWriter implements MemeWriter {

    private final ImgurIntegration imgurIntegration;
    private final ImgurImageUpload imgurImageUpload;

    @Override
    public String getName() {
        return "imgur";
    }

    @Override
    public String write(MemeTemplate memeTemplate)
            throws Exception {
        imgurIntegration.require();
        ImgurImage imgurImage = new ImgurImage(memeTemplate.getImage());
        return imgurImageUpload.upload(imgurImage);
    }
}
