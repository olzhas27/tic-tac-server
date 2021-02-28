package com.github.olzhas27.tic.tac.server.controller;

import com.github.olzhas27.tic.tac.server.dto.request.StepRequest;
import com.github.olzhas27.tic.tac.server.dto.request.WaitingEnemyRequest;
import com.github.olzhas27.tic.tac.server.dto.response.Response;
import com.github.olzhas27.tic.tac.server.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GameController {
    private final GameService gameService;

    @PostMapping("/step")
    public Response makeStep(@RequestBody StepRequest stepRequest) {
        return gameService.makeStep(stepRequest);
    }

    @GetMapping("/wait")
    public Response getEnemyStep(@RequestBody WaitingEnemyRequest waitingEnemyRequest) {
        return gameService.getEnemyStep(waitingEnemyRequest);
    }
}
