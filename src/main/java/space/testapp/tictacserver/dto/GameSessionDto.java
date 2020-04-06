package space.testapp.tictacserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import space.testapp.tictacserver.game.PlayerRole;
import space.testapp.tictacserver.model.GameStatus;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GameSessionDto extends Response {
    @JsonProperty("sessionId")
    private String sessionId;
    @JsonProperty("status")
    private GameStatus status;
    @JsonProperty("gameFieldJson")
    private String gameFieldJson;
    @JsonProperty
    private PlayerRole role;

    public GameSessionDto(String sessionId, String gameFieldJson, GameStatus status, PlayerRole role) {
        this.sessionId = sessionId;
        this.gameFieldJson = gameFieldJson;
        this.status = status;
        this.role = role;
    }
}
