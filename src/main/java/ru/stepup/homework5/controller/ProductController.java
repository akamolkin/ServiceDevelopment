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
import ru.stepup.homework5.dto.ProductRequest;
import ru.stepup.homework5.service.ProductService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "corporate-settlement-instance/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRegister(@Valid @RequestBody ProductRequest productRequest) {
        var res = productService.addProduct(productRequest);
        return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.CREATED);
    }
}
