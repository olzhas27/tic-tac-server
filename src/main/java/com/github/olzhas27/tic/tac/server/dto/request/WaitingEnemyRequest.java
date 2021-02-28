package com.github.olzhas27.tic.tac.server.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.olzhas27.tic.tac.server.exception.ValidationException;
import com.github.olzhas27.tic.tac.server.util.Validate;
import lombok.Data;

@Data
public class WaitingEnemyRequest implements Request {
    @JsonProperty("gameId")
    private String gameId;

    @JsonProperty("playerId")
    private String playerId;

    @Override
    public void validate() throws ValidationException {
        if (Validate.isNotUUID(gameId)
            || Validate.isNotUUID(playerId)) {
            throw new ValidationException("gameId and playerId should be UUID");
        }
    }
}
