package com.markusm44.kanban.common.api;

import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseMessage> handleValidationFailure(MethodArgumentNotValidException exception) {
    String message = exception.getBindingResult().getFieldErrors().stream()
        .map(error -> error.getField() + ": " + Objects.requireNonNullElse(error.getDefaultMessage(), "invalid value"))
        .distinct()
        .sorted()
        .collect(Collectors.joining("; "));

    return badRequest("Validation failed: " + message);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponseMessage> handleUnreadableRequest(HttpMessageNotReadableException exception) {
    return badRequest("Request body is missing or contains invalid JSON or field types.");
  }

  private ResponseEntity<ErrorResponseMessage> badRequest(String message) {
    return ResponseEntity.badRequest()
        .body(new ErrorResponseMessage(HttpStatus.BAD_REQUEST.value(), message));
  }
}