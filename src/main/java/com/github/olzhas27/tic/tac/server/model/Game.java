package com.github.olzhas27.tic.tac.server.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
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
    private Role winner;
    @Getter
    private Turn lastTurn;

    public Game(Player player1, Player player2) {
        if (player1.equals(player2)) {
            throw new IllegalStateException("Game must be between two different players");
        }
        this.player1 = player1;
        this.player2 = player2;
        field = createField();
        winner = null;
        lastTurn = null;
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
        if (nonNull(field.get(point)) || nonNull(getWinner())) {
            return false;
        }
        field.put(point, step);
        lastTurn = new Turn(step, point);
        return true;
    }

    public boolean hasNotEmptyCells() {
        return !field.containsValue(null);
    }

    public boolean waitsForPlayerStep(Player player) {
        if (hasNotEmptyCells()) {
            return false;
        }
        if (isNull(lastTurn)) {
            return player.getRole() == Role.X;
        }
        return lastTurn.getRole() != player.getRole();
    }

    public Role getWinner() {
        if (nonNull(winner)) {
            return winner;
        }
        final List<Role> winnerSet = Stream.of(
            checkLine(Point.of(0, 0), Point.of(0, 1), Point.of(0, 2)),
            checkLine(Point.of(1, 0), Point.of(1, 1), Point.of(1, 2)),
            checkLine(Point.of(2, 0), Point.of(2, 1), Point.of(2, 2)),
            checkLine(Point.of(0, 0), Point.of(1, 1), Point.of(2, 2)),
            checkLine(Point.of(0, 2), Point.of(1, 1), Point.of(2, 0)))
            .filter(Objects::nonNull)
            .distinct()
            .collect(Collectors.toList());
        if (winnerSet.size() == 1) {
            winner = winnerSet.get(0);
            return winner;
        }
        if (winnerSet.size() > 1) {
            throw new IllegalStateException("There must no be more than 1 winner at game");
        }
        return null;
    }

    private Role checkLine(Point point1, Point point2, Point point3) {
        final Role role1 = field.get(point1);
        final Role role2 = field.get(point2);
        final Role role3 = field.get(point3);
        if (role1 == role2 && role2 == role3) {
            return role1;
        } else {
            return null;
        }
    }
}
