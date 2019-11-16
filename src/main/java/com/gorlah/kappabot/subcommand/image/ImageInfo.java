package com.gorlah.kappabot.subcommand.image;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.jpa.entity.Image;
import com.gorlah.kappabot.jpa.repository.ImageRepository;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;

@Component
public class ImageInfo extends ImageSubcommand {
    
    public ImageInfo(ImageRepository imageRepository) {
        super(imageRepository);
    }
    
    @Override
    public String getName() {
        return "info";
    }
    
    @Override
    public String getHelpText() {
        return "Display info about an image.";
    }
    
    @Override
    public boolean isShownInHelp() {
        return true;
    }
    
    @Override
    public String process(Command command, ArrayList<String> parameters) {
        if (parameters.isEmpty()) {
            return "I need the name of an image you would like info for.";
        }
        
        Image image = getImage(parameters);
        
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
