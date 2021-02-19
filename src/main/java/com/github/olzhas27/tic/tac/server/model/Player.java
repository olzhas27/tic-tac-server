package com.github.olzhas27.tic.tac.server.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

import static com.github.olzhas27.tic.tac.server.model.Role.O;
import static com.github.olzhas27.tic.tac.server.model.Role.X;

@RequiredArgsConstructor
@Getter
public class Player {
    private final String id;
    private final Role role;

    public Role getOppositeRole() {
        switch (role) {
            case X:
                return O;
            case O:
                return X;
            default:
                throw new IllegalStateException("Unreachable code");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        final Player anotherPlayer = (Player) obj;
        return id.equals(anotherPlayer.getId());
    }
}
