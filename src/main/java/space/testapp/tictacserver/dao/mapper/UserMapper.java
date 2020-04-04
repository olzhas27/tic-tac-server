package space.testapp.tictacserver.dao.mapper;

import org.apache.ibatis.annotations.Param;
import space.testapp.tictacserver.model.User;

public interface UserMapper {
    void insert(@Param("user") User user);

    User select(@Param("login") String login);

    void update(@Param("user") User user);

    void removeToken(@Param("user") User user);
}
