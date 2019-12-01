package com.gorlah.kappabot.subcommand.root.meme.util;

import com.gorlah.kappabot.jpa.entity.Meme;
import com.gorlah.kappabot.jpa.repository.MemeRepository;
import com.gorlah.kappabot.meme.MemeTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class MemeSaver {

    private final MemeRepository memeRepository;

    Meme save(MemeTemplate memeTemplate, String url, String createdBy, List<String> parameters) {
        Meme meme = Meme.builder()
                .memeName(memeTemplate.getName())
                .parameters(String.join(" ", parameters))
                .url(url)
                .user(createdBy)
                .build();

        return memeRepository.save(meme);
    }
}
