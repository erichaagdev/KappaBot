package com.gorlah.kappabot.subcommand.image;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.jpa.entity.Image;
import com.gorlah.kappabot.jpa.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
    public boolean isShownInHelp() {
        return true;
    }
    
    @Override
    public String process(Command command, ArrayList<String> parameters) {
        if (parameters.isEmpty()) {
            return "I need the name of an image to display.";
        }
        
        Image image = imageHelper.getImage(parameters);
        
        if (image == null) {
            return "I couldn't find that image.";
        }
    
        image.setUseCount(image.getUseCount() + 1);
        
        imageRepository.save(image);
        
        return image.getUrl();
    }
}
