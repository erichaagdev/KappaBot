package com.gorlah.kappabot.subcommand.root.meme.util;

import com.gorlah.kappabot.jpa.entity.Meme;
import com.gorlah.kappabot.jpa.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class MemeFinder {

    private final MemeRepository memeRepository;

    Optional<String> findExisting(String name, List<String> parameters) {
        Meme meme = memeRepository.findByMemeNameAndParameters(name, String.join(" ", parameters));

        if (meme != null) {
            meme.incrementUseCount();
            memeRepository.save(meme);

            return Optional.of(meme.getUrl());
        }

        return Optional.empty();
    }
}
