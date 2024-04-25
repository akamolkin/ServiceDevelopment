package ru.stepup.homework5.dto;

import java.util.List;

public record ProductResponse(
        String instanceId,
        List<String> registerId,
        List<String> supplementaryAgreementId
) {
}
