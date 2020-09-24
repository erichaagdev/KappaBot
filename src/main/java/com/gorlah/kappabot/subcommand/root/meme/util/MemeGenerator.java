package com.gorlah.kappabot.subcommand.root.meme.util;

import com.gorlah.kappabot.meme.Meme;
import com.gorlah.kappabot.meme.MemeBuilder;
import com.gorlah.kappabot.meme.MemeTemplate;
import com.gorlah.kappabot.meme.components.ImageComponent;
import com.gorlah.kappabot.meme.components.MemeComponent;
import com.gorlah.kappabot.meme.components.TextComponent;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MemeGenerator {

    public Optional<MemeTemplate> populateTemplate(MemeTemplate template, List<String> parameters) {
        var components = new ArrayList<MemeComponent>();
        var parameterIndex = 0;

        for (var component : template.getComponents()) {
            if (!component.isOptional() && !hasMoreParameters(parameterIndex, parameters)) {
                return Optional.empty();
            } else if (!hasMoreParameters(parameterIndex, parameters)) {
                return Optional.of(template.withComponents(components));
            } else {
                var parameter = parameters.get(parameterIndex);
                if (component instanceof TextComponent c) {
                    if (component.isOptional() && urlFromString(parameter).isPresent()) {
                        components.add(component.withValue(c.getDefaultValue()));
                    } else {
                        components.add(component.withValue(parameter));
                        parameterIndex++;
                    }
                } else if (component instanceof ImageComponent c) {
                    components.add(component.withValue(parameter));
                    parameterIndex++;
                }
            }
        }

        return Optional.of(template.withComponents(components));
    }

    public Optional<Meme> fromTemplate(MemeTemplate template) {
        var memeBuilder = MemeBuilder.fromTemplate(template.getBaseImage());

        for (var component : template.getComponents()) {
            if (component instanceof TextComponent c) {
                var font = Font.decode(c.getFont()).deriveFont(c.getStyle(), c.getSize());
                var color = Color.decode(c.getColor());
                memeBuilder.writeText(component.getValue(), font, color, c.getX(), c.getY(), c.getWidth(), c.getHeight());
            } else if (component instanceof ImageComponent c) {
                var image = imageFromString(component.getValue());
                if (image.isEmpty()) {
                    return Optional.empty();
                }
                memeBuilder.drawImage(image.get(), c.getX(), c.getY(), c.getWidth(), c.getHeight());
            }
        }

        return Optional.of(memeBuilder.toMeme());
    }

    private boolean hasMoreParameters(int index, List<String> parameters) {
        return index < parameters.size();
    }

    private Optional<RenderedImage> imageFromString(String parameter) {
        return urlFromString(parameter)
                .flatMap(this::imageFromUrl);
    }

    private Optional<URL> urlFromString(String url) {
        try {
            return Optional.of(new URL(url));
        } catch (MalformedURLException e) {
            return Optional.empty();
        }
    }

    private Optional<RenderedImage> imageFromUrl(URL url) {
        try {
            return Optional.ofNullable(ImageIO.read(url));
        } catch (IOException ignored) {
            return Optional.empty();
        }
    }
}
