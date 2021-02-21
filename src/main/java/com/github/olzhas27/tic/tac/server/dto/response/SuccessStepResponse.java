package com.github.olzhas27.tic.tac.server.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SuccessStepResponse implements Response {
    @JsonProperty("gameId")
    private final String gameId;
}
