package com.gorlah.kappabot.integration.imgur;

import lombok.Value;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Value
public class ImgurImage {

    String image;
    String type = "base64";

    public ImgurImage(RenderedImage image) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.image = Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }
}
