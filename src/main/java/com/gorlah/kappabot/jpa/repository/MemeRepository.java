package com.gorlah.kappabot.jpa.repository;

import com.gorlah.kappabot.jpa.entity.SavedMeme;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemeRepository extends CrudRepository<SavedMeme, Integer> {

    Optional<SavedMeme> findByMemeNameAndParameters(String memeName, String parameters);
}
