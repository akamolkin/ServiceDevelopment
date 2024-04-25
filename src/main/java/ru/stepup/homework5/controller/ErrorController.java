package ru.stepup.homework5.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.stepup.homework5.exception.ExemplNotFoundException;
import ru.stepup.homework5.exception.FindDublException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        Violation violation = new Violation("Method Argument Not Valid", ex.getMessage(), errors);
        return new ResponseEntity<>(violation, status);
    }

    @ExceptionHandler(ExemplNotFoundException.class)
    public ResponseEntity<?> handleExemplNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(FindDublException.class)
    public ResponseEntity<?> handleFindDublException(Exception exception) {
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

  //  @ExceptionHandler(RuntimeException.class)
  //  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        StackTraceElement[] stackTraceElements = ex.getStackTrace();
        StringBuilder str = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            str.append(stackTraceElement.getClassName()).append(" : ").append(stackTraceElement.getMethodName());
        }
        str.append(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage() + " : " + str.toString()));

    }
}
