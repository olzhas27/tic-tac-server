package com.github.olzhas27.tic.tac.server.util;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class Validate {
    private Validate() {
        throw new IllegalStateException("This is utility class");
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean isUUID(String str) {
        if (isBlank(str)) {
            return false;
        }
        try {
            UUID.fromString(str);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    public static boolean isNotInclusiveBetween(int number, int start, int endInclusive) {
        return number < start || number > endInclusive;
    }
}
