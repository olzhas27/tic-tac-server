package space.testapp.tictacserver.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Component;
import space.testapp.tictacserver.dao.mapper.UserMapper;
import space.testapp.tictacserver.exception.UserErrorCode;
import space.testapp.tictacserver.exception.UserException;
import space.testapp.tictacserver.model.User;

import static java.util.Objects.isNull;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDao {
    private final UserMapper userMapper;

    public void insert(User user) throws UserException {
        try {
            userMapper.insert(user);
        } catch (UncategorizedSQLException e) {
            throw new UserException(UserErrorCode.login_already_exists);
        }
    }

    public User select(String login) throws UserException {
        val user = userMapper.select(login);
        if (isNull(user)) {
            throw new UserException(UserErrorCode.login_does_not_exists);
        }
        return user;
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void removeToken(User user) {
        userMapper.removeToken(user);
    }
}
