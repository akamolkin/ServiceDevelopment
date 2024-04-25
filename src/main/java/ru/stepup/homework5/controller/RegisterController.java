package ru.stepup.homework5.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stepup.homework5.dto.RegisterRequest;
import ru.stepup.homework5.service.RegisterService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "corporate-settlement-account/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {

    @Autowired
    private final RegisterService registerService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRegister(@Valid @RequestBody RegisterRequest registerRequest) {
        var res = registerService.addProductRegister(registerRequest);
        return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.CREATED);
    }
}
