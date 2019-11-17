package com.gorlah.kappabot.integration;

public interface KappaBotIntegration {

    String getName();

    boolean isEnabled();

    void initialize();

    default void require() {
        if (!isEnabled()) {
            throw new RuntimeException("Required integration is not available: " + getName());
        }
    }
}
