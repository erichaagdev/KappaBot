package com.gorlah.kappabot.subcommand;

import com.gorlah.kappabot.command.Command;
import com.gorlah.kappabot.subcommand.image.ImageAdd;
import com.gorlah.kappabot.subcommand.image.ImageInfo;
import com.gorlah.kappabot.subcommand.image.ImageList;
import com.gorlah.kappabot.subcommand.image.ImageMe;
import com.gorlah.kappabot.subcommand.image.ImageSearch;
import com.gorlah.kappabot.subcommand.image.ImageTop;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ImageCommand extends Subcommand {
    
    public ImageCommand(ImageAdd imageAdd,
                        ImageInfo imageInfo,
                        ImageList imageList,
                        ImageMe imageMe,
                        ImageSearch imageSearch,
                        ImageTop imageTop) {
        addSubcommand(imageAdd);
        addSubcommand(imageInfo);
        addSubcommand(imageList);
        addSubcommand(imageMe);
        addSubcommand(imageSearch);
        addSubcommand(imageTop);
    }
    
    @Override
    public String getName() {
        return "image";
    }
    
    @Override
    public String getHelpText() {
        return "For everything related to images.";
    }
    
    @Override
    public boolean isShownInHelp() {
        return true;
    }
    
    @Override
    public String process(Command command, ArrayList<String> parameters) {
        return null;
    }
}
