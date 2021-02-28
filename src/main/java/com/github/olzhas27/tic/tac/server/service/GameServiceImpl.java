package com.github.olzhas27.tic.tac.server.service;

import com.github.olzhas27.tic.tac.server.dao.GameContainer;
import com.github.olzhas27.tic.tac.server.dto.request.StepRequest;
import com.github.olzhas27.tic.tac.server.dto.request.WaitingEnemyRequest;
import com.github.olzhas27.tic.tac.server.dto.response.*;
import com.github.olzhas27.tic.tac.server.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GameServiceImpl implements GameService {
    private final GameContainer gameContainer;

    @Override
    public Response makeStep(@RequestBody StepRequest stepRequest) {
        stepRequest.validate();
        final Game game = gameContainer.search(stepRequest.getGameId());
        if (isNull(game)) {
            return ErrorResponse.NO_GAME;
        }
        final Player player = game.getPlayer(stepRequest.getPlayerId());
        if (isNull(player)) {
            return ErrorResponse.NO_GAME;
        }
        if (!game.waitsForPlayerStep(player)) {
            return ErrorResponse.SYNCHRONIZE_ERROR;
        }
        if (game.makeStepAt(Point.of(stepRequest.getX(), stepRequest.getY()), player.getRole())) {
            return new SuccessStepResponse(game.getId());
        } else {
            return ErrorResponse.SYNCHRONIZE_ERROR;
        }
    }

    @Override
    public Response getEnemyStep(WaitingEnemyRequest waitingEnemyRequest) {
        waitingEnemyRequest.validate();
        final Game game = gameContainer.search(waitingEnemyRequest.getGameId());
        if (isNull(game)) {
            return ErrorResponse.SYNCHRONIZE_ERROR;
        }
        final Player player = game.getPlayer(waitingEnemyRequest.getPlayerId());
        if (isNull(player)) {
            return ErrorResponse.SYNCHRONIZE_ERROR;
        }
        final Role winner = game.getWinner();
        if (nonNull(winner)) {
            return new WaitingEnemyResponse(winner, game.getLastTurn().getPoint());
        }
        if (game.waitsForPlayerStep(player)) {
            return new WaitingEnemyResponse(WaitingEnemyResponseCode.YOUR_TURN)
                .setPoint(game.getLastTurn());
        } else {
            return new WaitingEnemyResponse(WaitingEnemyResponseCode.STILL_WAITING);
        }
    }
}
