package space.testapp.tictacserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private static final ObjectMapper json = new ObjectMapper();

    @SneakyThrows
    @Override
    public String toString() {
        return json.writeValueAsString(this);
    }
}
