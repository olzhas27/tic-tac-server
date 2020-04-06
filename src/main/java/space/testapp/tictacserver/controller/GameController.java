package space.testapp.tictacserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.testapp.tictacserver.dto.Response;
import space.testapp.tictacserver.dto.StepDto;
import space.testapp.tictacserver.dto.TokenDto;
import space.testapp.tictacserver.service.GameService;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @PostMapping
    public Response ready(@RequestBody TokenDto tokenDto) {
        return gameService.ready(tokenDto);
    }

    @GetMapping("/{sessionId}")
    public Response getGameSessionInfo(@PathVariable String sessionId, @RequestParam String token) {
        return gameService.getGameSessionInfo(sessionId, token);
    }

    @PostMapping("/{sessionId}")
    public Response step(@RequestBody StepDto stepDto) {
        return gameService.step(stepDto);
    }
}
