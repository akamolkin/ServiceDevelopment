package ru.stepup.homework5.service;

import ru.stepup.homework5.dto.RegisterRequest;
import ru.stepup.homework5.dto.RegisterResponse;

public interface RegisterService {
    public RegisterResponse addProductRegister(RegisterRequest registerRequest);
}
