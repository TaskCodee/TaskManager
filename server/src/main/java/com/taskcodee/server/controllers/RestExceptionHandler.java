package com.taskcodee.server.controllers;

import com.taskcodee.server.APIs.ApiError;
import com.taskcodee.server.exceptions.EntityAlreadyExistsException;
import com.taskcodee.server.exceptions.MyEntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityAlreadyExistsException.class})
    protected ResponseEntity<Object> handleEntityAlreadyExistsEx(EntityAlreadyExistsException ex, WebRequest request) {
        ApiError apiError = new ApiError("Entity Already Exists", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({MyEntityNotFoundException.class, ConstraintViolationException.class})
    protected ResponseEntity<Object> handleEntityNotFoundEx(PersistenceException ex, WebRequest request) {
        ApiError apiError = new ApiError("Entity Not Found Exception", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiError apiError = new ApiError("Method Argument Not Valid", ex.getMessage());
        return new ResponseEntity<>(apiError, status);
    }
}
