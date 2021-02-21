package com.github.olzhas27.tic.tac.server.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.olzhas27.tic.tac.server.dto.response.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidationException extends RuntimeException implements Response {
    @JsonProperty("errorMessage")
    private final String successValidateConditions;
}
