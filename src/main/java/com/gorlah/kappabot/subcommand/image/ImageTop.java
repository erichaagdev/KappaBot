package com.gorlah.kappabot.subcommand.image;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.jpa.entity.Image;
import com.gorlah.kappabot.jpa.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageTop extends ImageSubcommand {

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
    public String process(Command command, ArrayList<String> parameters) {
        int num = 5;

        if (!parameters.isEmpty()) {
            try {
                num = Integer.parseInt(parameters.get(0));

                if (num > 25) {
                    num = 25;
                } else if (num < 1) {
                    num = 5;
                }
            } catch (Exception e) {
                // Exception is ignored because num is already defaulted.
            }
        }

        List<Image> images = imageRepository
                .findAll(PageRequest.of(0, num, Sort.by("useCount").descending()))
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
