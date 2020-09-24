package com.gorlah.kappabot.meme.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import lombok.With;

@With
@Value
public class TextComponent implements MemeComponent {

    int x;
    int y;
    int width;
    int height;
    int style;
    int size;
    boolean optional;
    String font;
    String color;
    String value;

    @JsonProperty("default")
    String defaultValue;

    @Override
    public MemeComponentType getType() {
        return MemeComponentType.TEXT;
    }
}
