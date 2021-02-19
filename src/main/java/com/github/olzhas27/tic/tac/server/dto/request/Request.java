package com.github.olzhas27.tic.tac.server.dto.request;

import com.github.olzhas27.tic.tac.server.exception.ValidationException;

public interface Request {
    void validate() throws ValidationException;
}
