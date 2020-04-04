package space.testapp.tictacserver.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TokenDto extends Response {
    private String token;

    public TokenDto() {
        token = UUID.randomUUID().toString();
    }
}
