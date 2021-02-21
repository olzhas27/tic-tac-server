package com.github.olzhas27.tic.tac.server.service;

import com.github.olzhas27.tic.tac.server.dao.GameContainer;
import com.github.olzhas27.tic.tac.server.dto.request.StepRequest;
import com.github.olzhas27.tic.tac.server.dto.request.WaitingEnemyRequest;
import com.github.olzhas27.tic.tac.server.dto.response.Response;
import com.github.olzhas27.tic.tac.server.model.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GameServiceImpl implements GameService {
    private final GameContainer gameContainer;

    @Override
    public Response makeStep(StepRequest stepRequest) {
        stepRequest.validate();
        final Game game = gameContainer.search(stepRequest.getGameId());

        return null;
    }

    @Override
    public Response getEnemyStep(WaitingEnemyRequest waitingEnemyRequest) {
        waitingEnemyRequest.validate();
        return null;
    }
}
