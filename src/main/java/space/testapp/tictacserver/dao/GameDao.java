package space.testapp.tictacserver.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.testapp.tictacserver.dao.mapper.GameMapper;
import space.testapp.tictacserver.model.Game;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GameDao {
    private final GameMapper gameMapper;

    public void insert(Game game) {
        gameMapper.insert(game);
    }

    public Game get(int userId) {
        return gameMapper.selectByUserId(userId);
    }

    public Game getNotCompletedGame(int userId) {
        return gameMapper.selectByUserIdNotCompletedGame(userId);
    }

    public Game get(String sessionId) {
        return gameMapper.select(sessionId);
    }

    public void update(Game game) {
        gameMapper.update(game);
    }
}
