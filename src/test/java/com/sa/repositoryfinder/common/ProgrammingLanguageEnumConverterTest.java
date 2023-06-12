package com.sa.repositoryfinder.common;

import com.sa.repositoryfinder.github.exception.ProgrammingLanguageNotSupportedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProgrammingLanguageEnumConverterTest {
    private final ProgrammingLanguageEnumConverter converter = new ProgrammingLanguageEnumConverter();

    @Test
    public void converterShouldReturnProgrammingLanguageEnumWhenSupportedProgrammingLanguageIsProvided() {
        String programmingLanguage = ProgrammingLanguageEnum.JAVA.name();

        ProgrammingLanguageEnum result = converter.convert(programmingLanguage);

        assertEquals(ProgrammingLanguageEnum.JAVA, result);
    }

    @Test
    public void converterShouldThrowProgrammingLanguageNotSupportedExceptionWhenInvalidLanguageIsProvided() {
        String programmingLanguage = "Not supported programming language";

        ProgrammingLanguageNotSupportedException exception = assertThrows(ProgrammingLanguageNotSupportedException.class, () -> {
            converter.convert(programmingLanguage);
        });

        assertTrue(exception.getMessage().contains(programmingLanguage));

    }
}
