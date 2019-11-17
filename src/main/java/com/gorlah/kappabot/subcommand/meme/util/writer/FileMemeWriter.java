package com.gorlah.kappabot.subcommand.meme.util.writer;

import com.gorlah.kappabot.meme.MemeTemplate;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.io.File;

@Component
public class FileMemeWriter implements MemeWriter {

    @Override
    public String getName() {
        return "file";
    }

    @Override
    public String write(MemeTemplate memeTemplate) throws Exception {
        File outputfile = new File("saved.png");
        ImageIO.write(memeTemplate.getImage(), "png", outputfile);
        return "file://" + outputfile.getAbsolutePath();
    }
}
