package com.sa.repositoryfinder.common;

import com.sa.repositoryfinder.github.exception.ProgrammingLanguageNotSupportedException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProgrammingLanguageEnumConverter implements Converter<String, ProgrammingLanguageEnum> {
    @Override
    public ProgrammingLanguageEnum convert(String value) {
        try {
            return ProgrammingLanguageEnum.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ProgrammingLanguageNotSupportedException("Programming language not supported: " + value);
        }
    }
}
