package com.github.olzhas27.tic.tac.server.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.olzhas27.tic.tac.server.exception.ValidationException;
import lombok.Data;

import static com.github.olzhas27.tic.tac.server.util.Validate.isNotInclusiveBetween;
import static org.apache.commons.lang3.StringUtils.isAnyBlank;

@Data
public class StepRequest implements Request {
    @JsonProperty("gameId")
    private String gameId;
    @JsonProperty("playerId")
    private String playerId;
    @JsonProperty("x")
    private int x;
    @JsonProperty("y")
    private int y;

    @Override
    public void validate() throws ValidationException {
        if (isNotInclusiveBetween(x, 0, 2)
            || isNotInclusiveBetween(y, 0, 2)
            || isAnyBlank(gameId, playerId)) {
            throw new ValidationException("x and y must be inclusive between 0 and 2. gameId and playerId must not be blank");
        }
    }
}
