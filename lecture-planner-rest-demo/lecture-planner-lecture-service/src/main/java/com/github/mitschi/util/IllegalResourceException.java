package com.github.mitschi.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown to indicate that the provided resource is malformed.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IllegalResourceException extends Exception {
    private static final long serialVersionUID = 1L;

    public IllegalResourceException(String message) {
        super(message);
    }
}