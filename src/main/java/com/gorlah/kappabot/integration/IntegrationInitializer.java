package com.gorlah.kappabot.integration;

import com.gorlah.kappabot.util.startup.StartupListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
class IntegrationInitializer implements StartupListener {

    private final Set<KappaBotIntegration> integrations;

    @Override
    public void onStartup() {
        integrations.forEach(KappaBotIntegration::initialize);
    }
}
