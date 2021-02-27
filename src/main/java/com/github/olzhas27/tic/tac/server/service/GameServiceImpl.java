package com.github.olzhas27.tic.tac.server.service;

import com.github.olzhas27.tic.tac.server.dao.GameContainer;
import com.github.olzhas27.tic.tac.server.dto.request.StepRequest;
import com.github.olzhas27.tic.tac.server.dto.request.WaitingEnemyRequest;
import com.github.olzhas27.tic.tac.server.dto.response.ErrorResponse;
import com.github.olzhas27.tic.tac.server.dto.response.Response;
import com.github.olzhas27.tic.tac.server.dto.response.SuccessStepResponse;
import com.github.olzhas27.tic.tac.server.model.Game;
import com.github.olzhas27.tic.tac.server.model.Player;
import com.github.olzhas27.tic.tac.server.model.Point;
import com.github.olzhas27.tic.tac.server.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import static java.util.Objects.isNull;

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
        if ((player.getRole() == Role.O && isNull(game.getLastTurn()))
            || player.getRole() == game.getLastTurn()) {
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
        return null;
    }
}
