package com.github.olzhas27.tic.tac.server.service;

import com.github.olzhas27.tic.tac.server.dto.request.RegisterRequest;
import com.github.olzhas27.tic.tac.server.dto.response.Response;

public interface RegistrationService {
    Response register(RegisterRequest registerRequest);
}
