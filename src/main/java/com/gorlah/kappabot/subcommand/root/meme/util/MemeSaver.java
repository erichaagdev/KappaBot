package com.gorlah.kappabot.subcommand.root.meme.util;

import com.gorlah.kappabot.jpa.entity.SavedMeme;
import com.gorlah.kappabot.jpa.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemeSaver {

    private final MemeRepository memeRepository;

    public SavedMeme save(String memeName, String url, String createdBy, List<String> parameters) {
        SavedMeme meme = SavedMeme.builder()
                .memeName(memeName)
                .parameters(String.join(" ", parameters))
                .url(url)
                .user(createdBy)
                .build();

        return memeRepository.save(meme);
    }
}
