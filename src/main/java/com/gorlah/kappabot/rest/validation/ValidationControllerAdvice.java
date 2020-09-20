package com.gorlah.kappabot.rest.validation;

import com.gorlah.kappabot.rest.validation.ValidationErrorResponse.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
class ValidationControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        var violations = e.getConstraintViolations()
                .stream()
                .map(violation -> new ValidationError(violation.getPropertyPath().toString(), violation.getMessage()))
                .collect(Collectors.toUnmodifiableList());
        return new ValidationErrorResponse(violations);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var violations = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(violation -> new ValidationError(violation.getField(), violation.getDefaultMessage()))
                .collect(Collectors.toUnmodifiableList());
        return new ValidationErrorResponse(violations);
    }

}
