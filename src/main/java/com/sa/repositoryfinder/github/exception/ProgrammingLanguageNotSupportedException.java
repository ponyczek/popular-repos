package com.sa.repositoryfinder.github.exception;

public class ProgrammingLanguageNotSupportedException extends RuntimeException {
    public ProgrammingLanguageNotSupportedException(String message) {
        super(message);
    }
}
