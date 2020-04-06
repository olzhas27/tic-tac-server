package space.testapp.tictacserver.dao.mapper;

import org.apache.ibatis.annotations.Param;
import space.testapp.tictacserver.model.Game;

public interface GameMapper {
    void insert(@Param("game") Game game);

    Game selectByUserId(@Param("userId") int userId);

    Game select(@Param("sessionId") String sessionId);

    void update(@Param("game") Game game);
}
