package com.lucasv.DesafioBackendJava.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class CustomExceptionTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CustomException#CustomException(String, HttpStatus)}
     *   <li>{@link CustomException#getHttpStatus()}
     *   <li>{@link CustomException#getMessage()}
     * </ul>
     */
    @Test
    void testConstructor() {
        CustomException actualCustomException = new CustomException("An error occurred", HttpStatus.CONTINUE);

        assertEquals(HttpStatus.CONTINUE, actualCustomException.getHttpStatus());
        assertEquals("An error occurred", actualCustomException.getMessage());
    }
}

