package com.github.olzhas27.tic.tac.server.controller;

import com.github.olzhas27.tic.tac.server.dto.request.RegisterRequest;
import com.github.olzhas27.tic.tac.server.dto.response.Response;
import com.github.olzhas27.tic.tac.server.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response register(@RequestBody RegisterRequest registerRequest) {
        return registrationService.register(registerRequest);
    }

}
