package ru.stepup.homework5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class FindDublException extends ResponseStatusException {
    public FindDublException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
