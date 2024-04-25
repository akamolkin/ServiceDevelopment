package ru.stepup.homework5.dto;

public record AgreesmentRequest(
    String generalAgreementId,
    String supplementaryAgreementId,
    String arrangementType,
    String number,
    String openingDate
) {
}
