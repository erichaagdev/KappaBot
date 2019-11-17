package com.gorlah.kappabot.rest.model;

import lombok.Data;

/**
 * @author ploober
 */
@Data
public class CommandRequest {

    private String command;
    private String user;
}
