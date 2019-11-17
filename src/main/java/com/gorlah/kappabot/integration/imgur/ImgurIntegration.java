package com.gorlah.kappabot.integration.imgur;

import com.google.common.base.Strings;
import com.gorlah.kappabot.integration.KappaBotIntegration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ImgurIntegration implements KappaBotIntegration {

    @Value("${imgur.clientId}")
    private String imgurClientId;

    @Override
    public String getName() {
        return "Imgur";
    }

    public boolean isEnabled() {
        return !Strings.isNullOrEmpty(imgurClientId);
    }

    @Override
    public void initialize() {
        if (!isEnabled()) {
            log.warn("Imgur integration disabled! ('imgur.clientId' property is not set)");
        }
    }

    public String getClientId() {
        return imgurClientId;
    }
}
