package com.github.olzhas27.tic.tac.server.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

public class Game {
    @Setter
    @Getter
    private String id;
    @Getter
    private final Player player1;
    @Getter
    private final Player player2;
    private Map<Point, Role> field;
    @Getter
    private Role lastTurn;

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

    public Player getPlayer(String playerId) {
        if (playerId.equals(player1.getId())) {
            return player1;
        }
        if (playerId.equals(player2.getId())) {
            return player2;
        }
        return null;
    }

    public synchronized boolean makeStepAt(Point point, Role step) {
        final Role currentStep = field.get(point);
        if (nonNull(currentStep)) {
            return false;
        }
        field.put(point, step);
        lastTurn = step;
        return true;
    }
}
