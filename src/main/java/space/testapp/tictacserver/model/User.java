package space.testapp.tictacserver.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

@Accessors(chain = true)
@Data
public class User {
    private int id;
    private String login;
    private UserStatus status;
    private String token;
    private String ip;
    private int port;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
            Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, status, token, ip, port);
    }
}
