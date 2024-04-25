package ru.stepup.homework5.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
    @NotNull(message = "Значение обязательного параметра instanceId не задано")
    Integer instanceId,
    String registryTypeCode,
    String accountType,
    String currencyCode,
    String branchCode,
    String priorityCode,
    String mdmCode,
    String clientCode,
    String trainRegion,
    String counter,
    String salesCode
) {
}
