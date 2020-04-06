package space.testapp.tictacserver.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import space.testapp.tictacserver.game.PlayerRole;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Slf4j
@Getter
public class Field {
    private static final ObjectMapper json = new ObjectMapper();
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 3;
    private final List<Cell> cells;

    public Field() {
        val cellCount = ROWS_COUNT * COLUMNS_COUNT;
        cells = new ArrayList<>(cellCount);
        for (int i = 0; i < cellCount; i++) {
            cells.add(new Cell(i));
        }
    }

    public Field(String string) {
        try {
            cells = new ArrayList<>(Arrays.asList(json.readValue(string, Cell[].class)));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public boolean hasEmptyCell() {
        return cells.stream()
            .anyMatch(Cell::isEmpty);
    }

    public PlayerRole getWinner() {
        return Stream.of(
            // horizontals
            getLine(0, 1, 2),
            getLine(3, 4, 5),
            getLine(6, 7, 8),
            //verticals
            getLine(0, 3, 6),
            getLine(1, 4, 7),
            getLine(2, 5, 8),
            //diagonals
            getLine(0, 4, 8),
            getLine(6, 4, 2)
        ).map(this::lineWinner)
            .filter(winner -> !isNull(winner))
            .findFirst()
            .orElse(null);
    }

    private List<Cell> getLine(int... indexes) {
        return Arrays.stream(indexes)
            .mapToObj(cells::get)
            .collect(Collectors.toList());
    }

    private PlayerRole lineWinner(List<Cell> cells) {
        if (!cells.get(0).isEmpty()
            && cells.get(0).getValue() == (cells.get(1).getValue())
            && cells.get(1).getValue() == (cells.get(2).getValue())) {
            return cells.get(0).getValue();
        } else {
            return null;
        }
    }

    @SneakyThrows
    @Override
    public String toString() {
        return json.writeValueAsString(cells);
    }

    public boolean setStep(int x, int y, PlayerRole role) {
        return cells.get(x * ROWS_COUNT + y).setValue(role);
    }
}
