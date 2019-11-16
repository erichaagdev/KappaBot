package com.gorlah.kappabot.subcommand.image;

import com.gorlah.kappabot.command.Subcommand;
import com.gorlah.kappabot.jpa.entity.Image;
import com.gorlah.kappabot.jpa.repository.ImageRepository;
import org.apache.commons.text.similarity.LongestCommonSubsequenceDistance;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class ImageSubcommand extends Subcommand {
    
    private static final LongestCommonSubsequenceDistance LCSD = new LongestCommonSubsequenceDistance();
    
    final ImageRepository imageRepository;
    
    public ImageSubcommand(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    
    Image getImage(ArrayList<String> parameters) {
        return getImage(String.join("", parameters).replaceAll("'", "").trim());
    }
    
    Image getImage(String alias) {
        Image image = imageRepository.findByAlias(alias);
        
        if (image == null) {
            List<Image> images = imageRepository.findByAliasContainsIgnoreCase(alias);
            
            int lowest = 0;
            
            for (Image tempImage: images) {
                int lcsd = LCSD.apply(alias, tempImage.getAlias());
                
                if (image == null || lcsd < lowest) {
                    image = tempImage;
                    lowest = lcsd;
                }
            }
        }
        
        return image;
    }
}
