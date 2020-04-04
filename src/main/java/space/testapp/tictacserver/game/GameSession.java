package space.testapp.tictacserver.game;


import lombok.extern.slf4j.Slf4j;
import lombok.val;
import space.testapp.tictacserver.model.User;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Slf4j
public class GameSession implements Runnable {
    private final User playerX;
    private final User playerO;
    private final PlayerRole[][] gameField;

    public GameSession(User playerX, User playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        gameField = new PlayerRole[3][3];
        for (val row : gameField) {
            Arrays.fill(row, 0, 3, null);
        }
    }

    @Override
    public void run() {
        Map<PlayerRole, Socket> roleSocketMap = new HashMap<>();
        try (
            val socketPlayerX = getConnection(playerX);
            val socketPlayerO = getConnection(playerO)
        ) {
            roleSocketMap.put(PlayerRole.X, socketPlayerX);
            roleSocketMap.put(PlayerRole.O, socketPlayerO);

            PlayerRole currentPlayer = PlayerRole.X;
            while (true) {
                val reader = new BufferedReader(new InputStreamReader(roleSocketMap.get(currentPlayer).getInputStream(), StandardCharsets.UTF_8));
                val nextStepX = Integer.parseInt(reader.readLine());
                val nextStepY = Integer.parseInt(reader.readLine());
                if (isNull(gameField[nextStepX][nextStepY])) {
                    gameField[nextStepX][nextStepY] = currentPlayer;

                    if (gameIsNotFinished() && gameHasNextStep()) {
                        currentPlayer = nextStep(currentPlayer);
                    } else {
                        PlayerRole winner = currentPlayer;
                        if (gameIsNotFinished()) {
                            winner = null;
                        }
                        notifyAboutGameEnd(winner, socketPlayerX, socketPlayerO);
                        break;
                    }
                } else {
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(roleSocketMap.get(currentPlayer).getOutputStream(), StandardCharsets.UTF_8));
                    writer.println("try again");
                }
            }

        } catch (IOException e) {
            log.warn("game session has not been done correctly", e);
        }
    }

    private Socket getConnection(User player) throws IOException {
        return new Socket(player.getIp(), player.getPort());
    }

    private PlayerRole nextStep(PlayerRole playerRole) {
        switch (playerRole) {
            case X:
                return PlayerRole.O;
            case O:
                return PlayerRole.X;
            default:
                return null;
        }
    }

    private boolean gameIsNotFinished() {
        return lineIsCompleted(gameField[0][0], gameField[0][1], gameField[0][2])
            && lineIsCompleted(gameField[1][0], gameField[1][1], gameField[1][2])
            && lineIsCompleted(gameField[2][0], gameField[2][1], gameField[2][2])

            && lineIsCompleted(gameField[0][0], gameField[1][0], gameField[2][0])
            && lineIsCompleted(gameField[0][1], gameField[1][1], gameField[2][1])
            && lineIsCompleted(gameField[0][2], gameField[1][2], gameField[2][2])

            && lineIsCompleted(gameField[0][0], gameField[1][1], gameField[2][2])
            && lineIsCompleted(gameField[0][2], gameField[1][1], gameField[2][0]);

    }

    private boolean lineIsCompleted(PlayerRole... line) {
        return !isNull(line[0]) && line[0] == line[1] && line[1] == line[2];
    }

    private boolean gameHasNextStep() {
        for (val row : gameField) {
            for (val cell : row) {
                if (isNull(cell)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void notifyAboutGameEnd(PlayerRole winner, Socket... recipients) throws IOException {
        for (Socket socket : recipients) {
            val writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            writer.println("winner is " + winner);
        }
    }
}
