package com.sa.repositoryfinder.github.api;

import com.sa.repositoryfinder.github.api.dto.GithubRequestValidationErrorDto;
import com.sa.repositoryfinder.github.exception.ProgrammingLanguageNotSupportedException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GithubExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GithubRequestValidationErrorDto handleMethodArgumentNotValid(MissingServletRequestParameterException ex) {
        String errorMessage = "Invalid request parameters: " + ex.getMessage();
        return new GithubRequestValidationErrorDto(errorMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GithubRequestValidationErrorDto handleMethodArgumentNotValid(ConstraintViolationException ex) {
        return new GithubRequestValidationErrorDto(ex.getMessage());
    }

    @ExceptionHandler(ProgrammingLanguageNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GithubRequestValidationErrorDto handleMethodArgumentNotValid(ProgrammingLanguageNotSupportedException ex) {
        return new GithubRequestValidationErrorDto(ex.getMessage());
    }
}
