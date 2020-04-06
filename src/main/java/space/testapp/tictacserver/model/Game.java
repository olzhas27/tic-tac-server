package space.testapp.tictacserver.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Game {
    private String sessionId;
    private int playerX;
    private int playerO;
    private int winner;
    private GameStatus status;
    private String fieldJson;
}
