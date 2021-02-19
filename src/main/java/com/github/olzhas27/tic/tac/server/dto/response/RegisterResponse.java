package com.github.olzhas27.tic.tac.server.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RegisterResponse implements Response {
    @JsonProperty("gameId")
    private final String gameId;
}
