package space.testapp.tictacserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.testapp.tictacserver.dao.GameDao;
import space.testapp.tictacserver.dao.UserDao;
import space.testapp.tictacserver.dto.GameSessionDto;
import space.testapp.tictacserver.dto.Response;
import space.testapp.tictacserver.dto.StepDto;
import space.testapp.tictacserver.dto.TokenDto;
import space.testapp.tictacserver.game.PlayerRole;
import space.testapp.tictacserver.model.*;

import java.util.UUID;

import static java.util.Objects.isNull;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GameService {
    private final UserDao userDao;
    private final GameDao gameDao;
    private User playerX;


    public synchronized Response ready(TokenDto tokenDto) {
        val user = userDao.selectByToken(tokenDto.getToken());
        val existingGame = gameDao.get(user.getId());
        if (!isNull(existingGame)) {
            val role = user.getId() == existingGame.getPlayerX() ? PlayerRole.X : PlayerRole.O;
            val gameSession = new GameSessionDto(existingGame.getSessionId(), existingGame.getFieldJson(), existingGame.getStatus(), role);
            log.info("returning to player {} existing game: {}", user, gameSession);
            return gameSession;
        }
        if (playerX == null || playerX.equals(user)) {
            user.setStatus(UserStatus.pending);
            userDao.update(user);
            playerX = user;
            return new Response();
        } else {
            val role = PlayerRole.O;
            val gameSession = new GameSessionDto()
                .setSessionId(UUID.randomUUID().toString())
                .setRole(role)
                .setGameFieldJson(new Field().toString())
                .setStatus(GameStatus.WAITING_FOR_X);

            val game = new Game()
                .setSessionId(gameSession.getSessionId())
                .setPlayerX(playerX.getId())
                .setPlayerO(user.getId())
                .setStatus(gameSession.getStatus())
                .setFieldJson(gameSession.getGameFieldJson());
            gameDao.insert(game);
            log.info("returning new game session: {}", gameSession);
            return gameSession;
        }
    }

    public Response getGameSessionInfo(String sessionId, String token) {
        val user = userDao.selectByToken(token);
        val game = gameDao.get(sessionId);
        val role = user.getId() == game.getPlayerX() ? PlayerRole.X : PlayerRole.O;
        val gameSessionDto = new GameSessionDto(sessionId, game.getFieldJson(), game.getStatus(), role);
        log.info("returning to player {} game session: {}", user, gameSessionDto);
        return gameSessionDto;
    }

    public Response step(String sessionId, StepDto stepDto) {
        val user = userDao.selectByToken(stepDto.getToken());
        log.info("user {} making step: {}", user, stepDto);
        val game = gameDao.get(sessionId);
        if (isUserStep(user, game)) {
            val field = new Field(game.getFieldJson());
            if (field.hasEmptyCell()) {
                val role = user.getId() == game.getPlayerX() ? PlayerRole.X : PlayerRole.O;
                val stepMade = field.setStep(stepDto.getX(), stepDto.getY(), role);
                if (stepMade) {
                    switch (role) {
                        case X:
                            game.setStatus(GameStatus.WAITING_FOR_O);
                            break;
                        case O:
                            game.setStatus(GameStatus.WAITING_FOR_X);
                            break;
                    }
                    game.setFieldJson(field.toString());
                    val winner = field.getWinner();
                    if (winner != null) {
                        switch (winner) {
                            case X:
                                game.setWinner(game.getPlayerX());
                                break;
                            case O:
                                game.setWinner(game.getPlayerO());
                                break;
                        }
                        game.setStatus(GameStatus.COMPLETED);
                    }
                }
            } else {
                game.setStatus(GameStatus.COMPLETED)
                    .setWinner(0);
            }
            gameDao.update(game);
            log.info("game updated: {}", game);
        } else {
            log.warn("user {} tries to make step {}", user, stepDto);
        }
        return new Response();
    }

    private boolean isUserStep(User user, Game game) {
        return (user.getId() == game.getPlayerX() && game.getStatus() == GameStatus.WAITING_FOR_X)
            || (user.getId() == game.getPlayerO() && game.getStatus() == GameStatus.WAITING_FOR_O);
    }
}
