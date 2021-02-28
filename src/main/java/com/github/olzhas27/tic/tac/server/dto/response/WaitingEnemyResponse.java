package com.github.olzhas27.tic.tac.server.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.olzhas27.tic.tac.server.model.Point;
import com.github.olzhas27.tic.tac.server.model.Role;
import com.github.olzhas27.tic.tac.server.model.Turn;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Setter
@Accessors(chain = true)
public class WaitingEnemyResponse implements Response {
    @JsonProperty("code")
    private final WaitingEnemyResponseCode code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("x")
    private Integer x;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("y")
    private Integer y;

    public WaitingEnemyResponse(Role role, Point point) {
        switch (role) {
            case X:
                this.code = WaitingEnemyResponseCode.X_WIN;
                break;
            case O:
                this.code = WaitingEnemyResponseCode.O_WIN;
                break;
            default:
                throw new IllegalArgumentException();
        }
        x = point.getX();
        y = point.getY();
    }

    public WaitingEnemyResponse setPoint(Turn lastTurn) {
        if (nonNull(lastTurn) && nonNull(lastTurn.getPoint())) {
            final Point point = lastTurn.getPoint();
            x = point.getX();
            y = point.getY();
        }
        return this;
    }
}
