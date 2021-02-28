package com.github.olzhas27.tic.tac.server.dao;

import com.github.olzhas27.tic.tac.server.model.Game;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameContainerImpl implements GameContainer {
    private Map<String, Game> gameMap = new ConcurrentHashMap<>();
    private Map<String, Game> playerIdGameMap = new ConcurrentHashMap<>();

    @Override
    public Game search(String gameId) {
        return gameMap.get(gameId);
    }

    @Override
    public String save(Game game) {
        final String gameId = UUID.randomUUID().toString();
        gameMap.put(gameId, game);
        playerIdGameMap.put(game.getPlayer1().getId(), game);
        playerIdGameMap.put(game.getPlayer2().getId(), game);
        game.setId(gameId);
        return gameId;
    }

    @Override
    public Game searchGameByPlayer(String playerId) {
        return playerIdGameMap.get(playerId);
    }
}
