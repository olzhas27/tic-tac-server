package com.github.olzhas27.tic.tac.server.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Turn {
    private final Role role;
    private final Point point;
}
