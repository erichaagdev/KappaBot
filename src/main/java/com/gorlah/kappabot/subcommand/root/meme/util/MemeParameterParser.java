package com.gorlah.kappabot.subcommand.root.meme.util;

import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MemeParameterParser {

    public List<String> parseMemeParameters(List<String> parameters) {
        var memeParameters = new ArrayList<String>();
        var parameterBuilder = new StringBuilder();

        for (var parameter : parameters) {
            var url = urlFromString(parameter);

            if (url.isPresent() || parameter.contains(" ")) {
                if (!parameterBuilder.isEmpty()) {
                    memeParameters.add(parameterBuilder.toString().trim());
                }

                memeParameters.add(parameter);
                parameterBuilder = new StringBuilder();
            } else {
                parameterBuilder.append(parameter).append(" ");
            }
        }

        if (!parameterBuilder.isEmpty()) {
            memeParameters.add(parameterBuilder.toString().trim());
        }

        return memeParameters;
    }

    private Optional<URL> urlFromString(String url) {
        try {
            return Optional.of(new URL(url));
        } catch (MalformedURLException e) {
            return Optional.empty();
        }
    }
}
