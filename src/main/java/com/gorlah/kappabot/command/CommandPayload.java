package com.gorlah.kappabot.command;

import com.gorlah.kappabot.function.BotRequestMetadata;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CommandPayload implements Iterable<String> {

    private final List<String> command;

    private List<String> parameters = new ArrayList<>();

    @Getter
    private BotRequestMetadata metadata;

    public CommandPayload(List<String> command, BotRequestMetadata metadata) {
        this.command = Collections.unmodifiableList(command);
        this.metadata = metadata;
    }

    public List<String> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    public void addParameter(String parameter) {
        this.parameters.add(parameter);
    }

    @NotNull
    @Override
    public Iterator<String> iterator() {
        return command.iterator();
    }
}
