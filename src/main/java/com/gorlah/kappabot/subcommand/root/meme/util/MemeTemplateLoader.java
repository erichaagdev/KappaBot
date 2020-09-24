package com.gorlah.kappabot.subcommand.root.meme.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gorlah.kappabot.meme.MemeTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemeTemplateLoader {

    private static final String rootDirectory = "classpath:memes";
    private static final String fileSuffix = ".template.json";

    private final ObjectMapper objectMapper;

    public Set<String> loadKnownMemes() {
        try (var files = Files.walk(ResourceUtils.getFile(rootDirectory).toPath())) {
            return files
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .map(File::getName)
                    .filter(fileName -> fileName.endsWith(fileSuffix))
                    .map(fileName -> fileName.substring(0, fileName.length() - fileSuffix.length()))
                    .collect(Collectors.toUnmodifiableSet());
        } catch (IOException e) {
            log.error("Failed to load known memes.", e);
        }

        return Collections.emptySet();
    }

    public Optional<MemeTemplate> load(String memeName) {
        return recurseDirectoryForFile(rootDirectory, memeName + ".template.json")
                .flatMap(this::createMemeTemplate);
    }

    private Optional<File> recurseDirectoryForFile(String directory, String filename) {
        try (var files = Files.walk(ResourceUtils.getFile(directory).toPath())) {
            return files
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(file -> filename.equals(file.getName()))
                    .findFirst();
        } catch (IOException e) {
            log.error("Failed to recurse directory '" + directory + "' for file '" + filename + "'", e);
        }

        return Optional.empty();
    }

    private Optional<MemeTemplate> createMemeTemplate(File file) {
        try {
            return Optional.of(objectMapper.readValue(file, MemeTemplate.class));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }
}
