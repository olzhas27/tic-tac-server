package space.testapp.tictacserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.testapp.tictacserver.dao.GameDao;
import space.testapp.tictacserver.dao.UserDao;
import space.testapp.tictacserver.dto.GetGameFieldDto;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GameService {
    private final UserDao userDao;
    private final GameDao gameDao;


}
