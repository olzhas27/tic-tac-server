package com.github.olzhas27.tic.tac.server.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.olzhas27.tic.tac.server.exception.ValidationException;
import lombok.Data;

import static org.apache.commons.lang3.StringUtils.isAnyBlank;

@Data
public class WaitingEnemyRequest implements Request {
    @JsonProperty("gameId")
    private String gameId;

    @JsonProperty("playerId")
    private String playerId;

    @Override
    public void validate() throws ValidationException {
        if (isAnyBlank(gameId, playerId)) {
            throw new ValidationException("gameId and playerId must not be blank");
        }
    }
}
