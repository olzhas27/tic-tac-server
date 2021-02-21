package com.github.olzhas27.tic.tac.server.dao;

import com.github.olzhas27.tic.tac.server.model.Game;

public interface GameContainer {
    Game search(String gameId);
    String save(Game game);
    Game searchGameByPlayer(String playerId);
}
