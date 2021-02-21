package com.github.olzhas27.tic.tac.server.service;


import com.github.olzhas27.tic.tac.server.dao.GameContainer;
import com.github.olzhas27.tic.tac.server.dto.response.ErrorResponse;
import com.github.olzhas27.tic.tac.server.dto.request.RegisterRequest;
import com.github.olzhas27.tic.tac.server.dto.response.RegisterResponse;
import com.github.olzhas27.tic.tac.server.dto.response.Response;
import com.github.olzhas27.tic.tac.server.model.Game;
import com.github.olzhas27.tic.tac.server.model.Player;
import com.github.olzhas27.tic.tac.server.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationServiceImpl implements RegistrationService {
    private static final long OFFLINE_PERIOD_SEC = 10;
    private final GameContainer gameContainer;
    private final Map<String, Instant> lastPollTimeMap = new ConcurrentHashMap<>();
    private final Queue<Player> waitingPlayers = new ArrayBlockingQueue<>(5);

    @Override
    public Response register(@RequestBody RegisterRequest registerRequest) {
        registerRequest.validate();

        final String playerId = registerRequest.getPlayerId();
        lastPollTimeMap.put(playerId, Instant.now());

        final Game existingGame = gameContainer.searchGameByPlayer(playerId);
        if (nonNull(existingGame)) {
            return new RegisterResponse(existingGame.getId());
        }

        final Player enemy = waitingPlayers.poll();
        if (isNull(enemy) || playerId.equals(enemy.getId())) {
            waitingPlayers.offer(new Player(playerId, Role.X));
            return ErrorResponse.WAITING_FOR_NEXT_PLAYER;
        }

        if (isOffline(enemy)) {
            waitingPlayers.offer(new Player(playerId, Role.X));
            lastPollTimeMap.remove(enemy.getId());
            return ErrorResponse.WAITING_FOR_NEXT_PLAYER;
        }

        final Player currentPlayer = new Player(playerId, enemy.getOppositeRole());
        final Game game = new Game(enemy, currentPlayer);
        final String newGameId = gameContainer.save(game);

        return new RegisterResponse(newGameId);
    }

    private boolean isOffline(Player player) {
        return Duration.between(lastPollTimeMap.get(player.getId()), Instant.now()).compareTo(Duration.ofSeconds(OFFLINE_PERIOD_SEC)) > 0;
    }
}
