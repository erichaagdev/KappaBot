package com.gorlah.kappabot.rest;

import com.gorlah.kappabot.rest.model.CommandRequest;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CommandServiceTestClient {

    public static void callRunCommand(String command, String expectedResponse) {
        var request = CommandRequest.builder()
                .author("Gorlah")
                .message(command)
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(request)
        .when()
                .post("/command/run")
                .prettyPeek()
        .then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo(expectedResponse));
    }
}
