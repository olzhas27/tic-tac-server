package space.testapp.tictacserver.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserException extends Exception {
    @JsonProperty("errorCode")
    private UserErrorCode errorCode;

    public UserException(UserErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "{\"errorCode\" : \"" + errorCode + "\"}";
    }
}
