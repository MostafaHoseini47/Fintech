package com.snapp.fintech.web.rest.exception;

import com.snapp.fintech.exception.NotFoundException;
import com.snapp.fintech.web.rest.exception.model.ErrorMessageModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorMessageModel> handleNotFoundException(NotFoundException ex) {
        ErrorMessageModel errorMessage = new ErrorMessageModel(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
