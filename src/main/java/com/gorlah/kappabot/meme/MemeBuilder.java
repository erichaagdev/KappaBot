package com.gorlah.kappabot.meme;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.RenderedImage;

public interface MemeBuilder {

    static MemeBuilder fromTemplate(String templateFilename) {
        return new MemeBuilderPipeline(templateFilename);
    }

    MemeBuilder writeText(String text, int x, int y, int width, int height);
    MemeBuilder writeText(String text, Color color, int x, int y, int width, int height);
    MemeBuilder writeText(String text, Font font, Color color, int x, int y, int width, int height);
    MemeBuilder drawImage(RenderedImage image, int x, int y, int width, int height);
    Meme toMeme();
}
