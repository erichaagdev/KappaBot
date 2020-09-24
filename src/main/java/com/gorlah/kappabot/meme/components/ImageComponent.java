package com.gorlah.kappabot.meme.components;

import lombok.Value;
import lombok.With;

@With
@Value
public class ImageComponent implements MemeComponent {

    int x;
    int y;
    int width;
    int height;
    boolean optional;
    String value;

    @Override
    public MemeComponentType getType() {
        return MemeComponentType.IMAGE;
    }
}
