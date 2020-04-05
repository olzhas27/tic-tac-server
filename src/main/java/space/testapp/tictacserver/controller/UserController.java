package space.testapp.tictacserver.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import space.testapp.tictacserver.dto.LogOutDto;
import space.testapp.tictacserver.dto.LoginDto;
import space.testapp.tictacserver.dto.Response;
import space.testapp.tictacserver.dto.TokenDto;
import space.testapp.tictacserver.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import space.testapp.tictacserver.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @PostMapping
    public TokenDto registerUser(@RequestBody LoginDto loginDto, HttpServletRequest request) throws UserException {
        log.info("request: {}", loginDto);
        val response = userService.registerUser(loginDto, request);
        log.info("response: {}", response);
        return response;
    }

    @DeleteMapping
    public Response logout(@RequestBody LogOutDto logOutDto) {
        log.info("request: {}", logOutDto);
        val response = userService.logout(logOutDto);
        log.info("response: {}", response);
        return response;
    }


}
