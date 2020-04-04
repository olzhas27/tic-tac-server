package space.testapp.tictacserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LogOutDto {
    @JsonProperty("token")
    private String token;
}
