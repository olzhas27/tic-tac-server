package com.github.olzhas27.tic.tac.server.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Game {
    @Setter
    private String id;
    private final Player player1;
    private final Player player2;
    private Map<Point, Role> field;

    public Game(Player player1, Player player2) {
        if (player1.equals(player2)) {
            throw new IllegalStateException("Game must be between two different players");
        }
        this.player1 = player1;
        this.player2 = player2;
        field = createField();
    }

    private Map<Point, Role> createField() {
        Map<Point, Role> field = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field.put(Point.of(i, j), null);
            }
        }
        return field;
    }
}
