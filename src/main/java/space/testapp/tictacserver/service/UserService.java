package space.testapp.tictacserver.service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import space.testapp.tictacserver.dao.UserDao;
import space.testapp.tictacserver.dto.LogOutDto;
import space.testapp.tictacserver.dto.LoginDto;
import space.testapp.tictacserver.dto.Response;
import space.testapp.tictacserver.dto.TokenDto;
import space.testapp.tictacserver.exception.UserErrorCode;
import space.testapp.tictacserver.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.testapp.tictacserver.model.User;
import space.testapp.tictacserver.model.UserStatus;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserService {
    private final UserDao userDao;

    public TokenDto registerUser(LoginDto loginDto, HttpServletRequest request) throws UserException {
        log.debug("loginDto: {}", loginDto);
        val login = loginDto.getLogin();
        checkLogin(login);

        val token = new TokenDto();
        val user = new User()
            .setLogin(login)
            .setStatus(UserStatus.authorized)
            .setToken(token.getToken())
            .setIp(request.getRemoteAddr())
            .setPort(request.getRemotePort());
        userDao.insert(user);
        log.info("user registered: {}", user);
        return token;
    }

    public TokenDto loginUser(LoginDto loginDto, HttpServletRequest request) throws UserException {
        val login = loginDto.getLogin();
        checkLogin(login);
        val user = userDao.select(login);
        TokenDto newToken = new TokenDto();
        user.setIp(request.getRemoteAddr())
            .setPort(request.getRemotePort())
            .setToken(newToken.getToken());
        userDao.update(user);
        return newToken;

    }

    private void checkLogin(String login) throws UserException {
        if (login == null || login.isEmpty()) {
            throw new UserException(UserErrorCode.login_is_empty);
        }
    }

    public Response logout(LogOutDto logOutDto) {
        val user = new User()
            .setToken(logOutDto.getToken())
            .setStatus(UserStatus.none);
        userDao.removeToken(user);
        return new Response();
    }
}
