package ru.stepup.homework5.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProductRequest(
        Integer instanceId,
        @NotNull(message = "Значение обязательного параметра productType не задано")
        String productType,
        @NotNull(message = "Значение обязательного параметра productCode не задано")
        String productCode,
        @NotNull(message = "Значение обязательного параметра registerType не задано")
        String registerType,
        @NotNull(message = "Значение обязательного параметра mdmCode не задано")
        String mdmCode,
        @NotNull(message = "Значение обязательного параметра contractNumber не задано")
        String contractNumber,
        @NotNull(message = "Значение обязательного параметра contractDate не задано")
        String contractDate,
        @NotNull(message = "Значение обязательного параметра priority не задано")
        Integer priority,
        @NotNull(message = "Значение обязательного параметра contractId не задано")
        Integer contractId,
        @NotNull(message = "Значение обязательного параметра branchCode не задано")
        String branchCode,
        @NotNull(message = "Значение обязательного параметра isoCurrencyCode не задано")
        String isoCurrencyCode,
        @NotNull(message = "Значение обязательного параметра urgencyCode не задано")
        String urgencyCode,

//ReferenceCode	integer
        List<AgreesmentRequest> instanceArrangement

) {
}
