package com.gorlah.kappabot.rest;

import com.gorlah.kappabot.rest.model.CommandRequest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CommandServiceClient {

    public static void callRunCommand(CommandRequest request, String expectedResponse) {
        given()
                .contentType(ContentType.JSON)
                .body(request)
            .when()
                .post("/command/run")
            .then()
                .body("response", equalTo(expectedResponse));
    }
}
