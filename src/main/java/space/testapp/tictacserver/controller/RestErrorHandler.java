package space.testapp.tictacserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import space.testapp.tictacserver.exception.UserException;


@Slf4j
@ControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestErrorHandler {
    private final ObjectMapper json;

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String processValidationError(UserException userException) {
        val response = userException.toString();
        log.info("response: {}", response);
        return response;
    }
}
