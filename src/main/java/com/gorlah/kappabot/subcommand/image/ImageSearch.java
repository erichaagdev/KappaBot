package com.gorlah.kappabot.subcommand.image;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.jpa.entity.Image;
import com.gorlah.kappabot.jpa.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageSearch extends ImageSubcommand {

    private final ImageRepository imageRepository;
    private final ImageHelper imageHelper;

    @Override
    public String getName() {
        return "search";
    }

    @Override
    public String getHelpText() {
        return "Search for images.";
    }

    @Override
    public String process(Command command, ArrayList<String> parameters) {
        if (parameters.isEmpty()) {
            return "You need to pass me a search query.";
        }

        String alias = imageHelper.stripQuery(parameters);

        if (alias.length() < 3) {
            return "Try a search query of 3 or more characters.";
        }

        List<Image> images = imageRepository.findByAliasContainsIgnoreCase(alias);

        if (images == null || images.isEmpty()) {
            return "I didn't find anything for `" + alias + "`.";
        }

        StringBuilder builder =
                new StringBuilder("Here's what I found for `").append(alias).append("`: ");

        for (Image image : images) {
            builder.append(image.getAlias()).append(", ");
        }

        return builder.substring(0, builder.length() - 2);
    }
}
