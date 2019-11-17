package com.gorlah.kappabot.util.startup;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
class StartupBroadcaster implements ApplicationListener<ContextRefreshedEvent> {

    private final Set<StartupListener> startupListeners;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        startupListeners.forEach(StartupListener::onStartup);
    }
}
