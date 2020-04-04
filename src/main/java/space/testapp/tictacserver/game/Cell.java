package space.testapp.tictacserver.game;

import lombok.Data;

@Data
public class Cell {
    private int x;
    private int y;
    private PlayerRole role;
}
