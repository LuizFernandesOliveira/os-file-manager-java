package com.osfilemanager.exception;

import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = InternalServerErrorException.class)
  public ResponseEntity<HTTPErrorResponse> domainException(
      final InternalServerErrorException exception) {
    return ResponseEntity.internalServerError()
        .body(HTTPErrorResponse.builder().message(exception.getMessage()).build());
  }
}
