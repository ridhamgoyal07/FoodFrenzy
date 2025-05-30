package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExceptionsTest {

    @Autowired
    private Exceptions exceptionHandler;

    @Test
    public void testHandlerReturnsExceptionView() {
        // Given
        Exception testException = new RuntimeException("Test exception");

        // When
        String viewName = exceptionHandler.handler();

        // Then
        assertEquals("exception", viewName, "Handler should return 'exception' view name");
    }

    @Test
    public void testHandlerLogsMessage() {
        // Given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // When
            exceptionHandler.handler();

            // Then
            assertEquals("Exception Handled....!!!!\n", outputStream.toString(), 
                "Handler should log the expected message");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testResponseStatusAnnotation() {
        // Given
        ResponseStatus annotation = exceptionHandler.getClass()
            .getMethod("handler")
            .getAnnotation(ResponseStatus.class);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, annotation.value(), 
            "Handler should have INTERNAL_SERVER_ERROR status");
    }
}
