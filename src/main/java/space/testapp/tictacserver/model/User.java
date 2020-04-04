package space.testapp.tictacserver.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class User {
    private int id;
    private String login;
    private UserStatus status;
    private String token;
    private String ip;
    private int port;
}
