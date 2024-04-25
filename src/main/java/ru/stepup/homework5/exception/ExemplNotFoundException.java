package ru.stepup.homework5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExemplNotFoundException extends ResponseStatusException {
    public ExemplNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
