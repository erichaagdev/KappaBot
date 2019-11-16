package com.gorlah.kappabot.jpa.repository;

import com.gorlah.kappabot.jpa.entity.Meme;
import org.springframework.data.repository.CrudRepository;

public interface MemeRepository extends CrudRepository<Meme, Integer> {

    Meme findByMemeNameAndParameters(String memeName, String parameters);
}
