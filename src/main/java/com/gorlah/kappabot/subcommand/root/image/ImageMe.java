package com.gorlah.kappabot.subcommand.root.image;

import com.gorlah.kappabot.command.CommandPayload;
import com.gorlah.kappabot.jpa.entity.Image;
import com.gorlah.kappabot.jpa.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageMe extends ImageSubcommand {

    private final ImageRepository imageRepository;
    private final ImageHelper imageHelper;

    @Override
    public String getName() {
        return "me";
    }

    @Override
    public String getHelpText() {
        return "Displays an image.";
    }

    @Override
    public String process(CommandPayload payload) {
        val parameters = payload.getParameters();

        if (parameters.isEmpty()) {
            return "I need the name of an image to display.";
        }

        Image image = imageHelper.getImageFuzzy(parameters);

        if (image == null) {
            return "I couldn't find that image.";
        }

        image.setUseCount(image.getUseCount() + 1);

        imageRepository.save(image);

        return image.getUrl();
    }
}
