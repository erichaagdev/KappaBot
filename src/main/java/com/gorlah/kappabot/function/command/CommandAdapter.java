package com.gorlah.kappabot.function.command;

public interface CommandAdapter {

    String getCommandPrefix();

    String getSource();

    boolean isEnabled();
}
