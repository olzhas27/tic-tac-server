package space.testapp.tictacserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StepDto {
    @JsonProperty("token")
    private String token;
    @JsonProperty("x")
    private int x;
    @JsonProperty("y")
    private int y;
}
