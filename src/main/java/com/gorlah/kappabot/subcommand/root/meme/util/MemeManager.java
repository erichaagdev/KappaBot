package com.gorlah.kappabot.subcommand.root.meme.util;

import com.gorlah.kappabot.command.CommandPayload;
import com.gorlah.kappabot.subcommand.root.meme.util.writer.MemeWriter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class MemeManager {

    private final MemeFinder memeFinder;
    private final MemeSaver memeSaver;
    private final MemeFactory memeFactory;
    private final Set<MemeWriter> memeWriters;

    public String getOrCreate(String memeName, String writerName, CommandPayload payload, boolean save)
            throws Exception {
        val parameters = payload.getParameters();

        if (save) {
            var url = memeFinder.findExisting(memeName, parameters).orElse(null);

            if (url != null) {
                return url;
            }
        }

        var template = memeFactory.create(memeName, parameters);
        var writer = getWriter(writerName);
        var url = writer.write(template);

        if (save) {
            url = memeSaver.save(template, url, payload.getMetadata().getAuthor(), parameters).getUrl();
        }

        return url;
    }

    private MemeWriter getWriter(String writerName) {
        return memeWriters.stream()
                .filter(writer -> writer.getName().equalsIgnoreCase(writerName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown meme writer: " + writerName));
    }
}
