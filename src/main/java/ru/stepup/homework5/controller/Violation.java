package ru.stepup.homework5.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Violation {
    private String message;
    private String debugMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;
    public Violation(String message, String debugMessage){
        this.message=message;
        this.debugMessage=debugMessage;
    }
}
