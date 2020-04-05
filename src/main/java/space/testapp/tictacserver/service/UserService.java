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
import space.testapp.tictacserver.utils.WebUtils;

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
            .setIp(WebUtils.getClientIp(request))
            .setPort(loginDto.getPort());
        try {
            userDao.insert(user);
        } catch (UserException e) {
            if (e.getErrorCode() == UserErrorCode.login_already_exists) {
                userDao.update(user);
            }
        }
        log.info("user registered: {}", user);
        return token;
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
