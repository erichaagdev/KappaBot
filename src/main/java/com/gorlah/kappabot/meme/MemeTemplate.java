package com.gorlah.kappabot.meme;

import com.gorlah.kappabot.meme.components.MemeComponent;
import lombok.Value;
import lombok.With;

import java.util.List;
import java.util.stream.Collectors;

@With
@Value
public class MemeTemplate {

    String name;
    String baseImage;
    String description;
    List<MemeComponent> components;

    public String getUsage() {
        return components
                .stream()
                .map(MemeComponent::getType)
                .map(Enum::toString)
                .map(type -> "<" + type + ">")
                .collect(Collectors.joining(", "));
    }
}
