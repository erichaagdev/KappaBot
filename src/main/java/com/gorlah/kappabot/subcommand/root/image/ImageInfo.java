package com.gorlah.kappabot.subcommand.root.image;

import com.gorlah.kappabot.command.CommandPayload;
import com.gorlah.kappabot.jpa.entity.Image;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class ImageInfo extends ImageSubcommand {

    private final ImageHelper imageHelper;

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getHelpText() {
        return "Display info about an image.";
    }

    @Override
    public String process(CommandPayload payload) {
        val parameters = payload.getParameters();

        if (parameters.isEmpty()) {
            return "I need the name of an image you would like info for.";
        }

        Image image = imageHelper.getImageFuzzy(parameters);

        if (image == null) {
            return "I couldn't find that image.";
        }

        String user = image.getUser() == null || image.getUser().isEmpty() ? "null" : image.getUser();
        int useCount = image.getUseCount();

        return MessageFormat
                .format("Image ''{0}'' was added by {1} on {2} and has been used {3} time{4}.",
                        image.getAlias(),
                        user,
                        image.getAdded().toString(),
                        useCount,
                        useCount == 1 ? "" : "s");
    }
}
