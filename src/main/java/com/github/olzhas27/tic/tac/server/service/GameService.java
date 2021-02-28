package com.github.olzhas27.tic.tac.server.service;

import com.github.olzhas27.tic.tac.server.dto.request.StepRequest;
import com.github.olzhas27.tic.tac.server.dto.request.WaitingEnemyRequest;
import com.github.olzhas27.tic.tac.server.dto.response.Response;

public interface GameService {
    Response makeStep(StepRequest stepRequest);

    Response getEnemyStep(WaitingEnemyRequest waitingEnemyRequest);
}
