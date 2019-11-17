package com.gorlah.kappabot.integration.imgur;

import lombok.Value;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Value
public class ImgurImage {

    private String image;
    private String type = "base64";

    public ImgurImage(BufferedImage image) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.image = Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }
}
