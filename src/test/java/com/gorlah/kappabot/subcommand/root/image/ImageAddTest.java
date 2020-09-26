package com.gorlah.kappabot.subcommand.root.image;

import com.gorlah.kappabot.annotation.KappaBotIntegrationTest;
import com.gorlah.kappabot.jpa.entity.Image;
import com.gorlah.kappabot.jpa.repository.ImageRepository;
import com.gorlah.kappabot.rest.CommandServiceTestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import static org.hamcrest.Matchers.equalTo;

@KappaBotIntegrationTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class ImageAddTest {

    @Autowired private ImageRepository imageRepository;

    @Test
    void processImageAdd() {
        CommandServiceTestClient.callRunCommand(
                "/kb image add nod https://i.imgur.com/6ADLV4r.gif",
                equalTo("Added image 'nod' to image set."));
    }

    @Test
    void processImageAddWhenFuzzyImageExists() {
        var image = Image.builder()
                .alias("nod2")
                .url("http://awesomenator.com/content/2012/12/jack-gif.gif")
                .user("Gorlah")
                .build();
        imageRepository.save(image);

        CommandServiceTestClient.callRunCommand(
                "/kb image add nod https://i.imgur.com/6ADLV4r.gif",
                equalTo("Added image 'nod' to image set."));
    }
}
