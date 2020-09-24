package com.gorlah.kappabot.subcommand.root.meme.util;

import com.gorlah.kappabot.jpa.entity.SavedMeme;
import com.gorlah.kappabot.jpa.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SavedMemeService {

    private final MemeRepository memeRepository;

    public Optional<SavedMeme> findExisting(String name, List<String> parameters) {
        return memeRepository.findByMemeNameAndParameters(name, String.join(" ", parameters))
                .map(SavedMeme::incrementUseCount)
                .map(memeRepository::save);
    }

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
