package space.testapp.tictacserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetGameFieldDto {
    @JsonProperty("tokenId")
    private String tokenId;
    @JsonProperty("sessionId")
    private String sessionId;
}
