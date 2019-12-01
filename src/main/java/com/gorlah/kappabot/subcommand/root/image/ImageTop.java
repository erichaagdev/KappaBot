package com.gorlah.kappabot.subcommand.root.image;

import com.gorlah.kappabot.command.CommandPayload;
import com.gorlah.kappabot.jpa.entity.Image;
import com.gorlah.kappabot.jpa.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageTop extends ImageSubcommand {

    private static final int DEFAULT_TO_DISPLAY = 5;
    private static final int MIN_TO_DISPLAY = 1;
    private static final int MAX_TO_DISPLAY = 25;

    private final ImageRepository imageRepository;

    @Override
    public String getName() {
        return "top";
    }

    @Override
    public String getHelpText() {
        return "Displays the top 5 images. Pass a number up to 25 to see more.";
    }

    @Override
    public String process(CommandPayload payload) {
        val parameters = payload.getParameters();
        var amountToDisplay = DEFAULT_TO_DISPLAY;

        if (!parameters.isEmpty()) {
            try {
                amountToDisplay = Integer.parseInt(parameters.get(0));

                if (amountToDisplay > MAX_TO_DISPLAY) {
                    amountToDisplay = MAX_TO_DISPLAY;
                } else if (amountToDisplay < MIN_TO_DISPLAY) {
                    amountToDisplay = MAX_TO_DISPLAY;
                }
            } catch (Exception e) {
                // Exception is ignored because amountToDisplay is already defaulted.
            }
        }

        List<Image> images = imageRepository
                .findAll(PageRequest.of(0, amountToDisplay, Sort.by("useCount").descending()))
                .toList();

        StringBuilder builder = new StringBuilder("The top most used images are:\n```\n");

        for (Image image : images) {
            builder.append(String.format("%6d", image.getUseCount()))
                    .append("\t")
                    .append(image.getAlias())
                    .append("\n");
        }

        builder.append("```");

        return builder.toString();
    }
}
