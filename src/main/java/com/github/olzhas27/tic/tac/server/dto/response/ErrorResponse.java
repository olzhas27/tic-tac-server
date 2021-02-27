package com.github.olzhas27.tic.tac.server.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class ErrorResponse implements Response {
    public static final ErrorResponse INVALID_GAME_ID_FORMAT = new ErrorResponse("GAME ID MUST BE UUID");
    public static final ErrorResponse WAITING_FOR_NEXT_PLAYER = new ErrorResponse("THERE IS NO PLAYER");
    public static final Response NO_GAME = new ErrorResponse("THERE IS NO GAME SESSION");
    public static final Response SYNCHRONIZE_ERROR = new ErrorResponse("SYNCHRONIZATION ERROR");

    public static ErrorResponse validationErrorWith(String conditions) {
        return new ErrorResponse("Validation error. Response must feet next conditions: " + conditions);
    }

    @JsonProperty("errorMessage")
    private final String errorMessage;
}
