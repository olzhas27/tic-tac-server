package space.testapp.tictacserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.testapp.tictacserver.dto.LoginDto;
import space.testapp.tictacserver.dto.Response;
import space.testapp.tictacserver.dto.TokenDto;
import space.testapp.tictacserver.service.GameService;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;


}
