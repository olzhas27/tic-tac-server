package space.testapp.tictacserver.exception;

public class UserException extends Exception {
    private UserErrorCode errorCode;

    public UserException(UserErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "{\"errorCode\" : \"" + errorCode + "\"}";
    }
}
