package space.testapp.tictacserver.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.testapp.tictacserver.dao.mapper.GameMapper;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GameDao {
    private final GameMapper gameMapper;
}
