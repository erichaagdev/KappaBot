package com.gorlah.kappabot.subcommand.root.meme.util.writer;

import com.gorlah.kappabot.meme.Meme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class MemeFileWriter implements MemeWriter {

    @Override
    public String getName() {
        return "file";
    }

    @Override
    public Optional<String> write(Meme meme) {
        File outputFile = new File("saved.png");

        try {
            ImageIO.write(meme.asImage(), "png", outputFile);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }

        return Optional.of("file://" + outputFile.getAbsolutePath());
    }
}
