package com.github.olzhas27.tic.tac.server.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.olzhas27.tic.tac.server.exception.ValidationException;
import com.github.olzhas27.tic.tac.server.util.Validate;
import lombok.Data;

@Data
public class RegisterRequest implements Request {
    @JsonProperty("playerId")
    private String playerId;

    @Override
    public void validate() throws ValidationException {
        if (!Validate.isUUID(playerId)) {
            throw new ValidationException("playerId must be UUID format");
        }
    }
}
