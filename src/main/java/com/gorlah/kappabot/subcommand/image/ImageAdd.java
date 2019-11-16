package com.gorlah.kappabot.subcommand.image;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.jpa.entity.Image;
import com.gorlah.kappabot.jpa.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class ImageAdd extends ImageSubcommand {
    
    private final ImageRepository imageRepository;
    private final ImageHelper imageHelper;
    
    @Override
    public String getName() {
        return "add";
    }
    
    @Override
    public String getHelpText() {
        return "Adds an image to the database. Takes an image alias and URL as parameters.";
    }
    
    @Override
    public boolean isShownInHelp() {
        return true;
    }
    
    @Override
    public String process(Command command, ArrayList<String> parameters) {
        if (parameters.isEmpty()) {
            return "I need an image alias and URL to store.";
        } else if (parameters.size() == 1) {
            return "I need an image URL to store.";
        }
        
        Image image = imageHelper.getImage(parameters.get(0).replaceAll("\\s|'", ""));
        
        if (image != null) {
            return "An image with that alias already exists.";
        }
        
        image = new Image();
        image.setAlias(parameters.get(0).replaceAll("\\s", ""));
        image.setUrl(String.join(" ", parameters.subList(1, parameters.size())));
        image.setUser(command.getCalledBy());
        
        image = imageRepository.save(image);
        
        return "Added image '" + image.getAlias() + "' to image set.";
    }
}
