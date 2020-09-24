package com.gorlah.kappabot.meme.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonSubTypes(value = {
        @Type(value = ImageComponent.class, name = "IMAGE"),
        @Type(value = TextComponent.class, name = "TEXT")
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
public interface MemeComponent {

    MemeComponentType getType();
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    boolean isOptional();

    @JsonIgnore
    String getValue();
    MemeComponent withValue(String value);
}
