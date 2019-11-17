package com.gorlah.kappabot.jpa.repository;

import com.gorlah.kappabot.jpa.entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, String>, PagingAndSortingRepository<Image, String> {

    Image findByAlias(String alias);

    List<Image> findByAliasContainsIgnoreCase(String alias);
}
