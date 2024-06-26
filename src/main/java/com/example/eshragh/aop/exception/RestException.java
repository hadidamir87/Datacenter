package com.example.eshragh.aop.exception;


import com.example.eshragh.aop.exception.handedOfExceptions.ResponseForClient;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@ControllerAdvice

public class RestException {
    private final Properties properties = new Properties();
    @PostConstruct
    public void init() {
        try {
            properties.load(new FileReader(
                    "D:\\H1\\JOB\\programming\\Java\\develop\\demo1\\eshragh\\src\\main\\resources\\exception_fa_IR.properties",
                    StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ExceptionHandler(CustomServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseForClient> handleException(CustomServiceException exception) {
        ResponseForClient value = new ResponseForClient();
        value.setError(true);
        String property = properties.getProperty(exception.getErrorCode());
        if (property == null) {
            property = properties.getProperty("default");
        }
        value.setMessage(property);
        return ResponseEntity.badRequest().body(value);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseForClient> handleException(RuntimeException exception) {
        ResponseForClient value = new ResponseForClient();
        value.setError(true);
        value.setMessage(properties.getProperty("unknown"));
        exception.printStackTrace();
        return ResponseEntity.badRequest().body(value);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseForClient> getException(MethodArgumentNotValidException exception) {

        ResponseForClient exceptionResponse = new ResponseForClient();
        exceptionResponse.setError(true);
        FieldError fieldError = exception.getBindingResult().getFieldError();
        String message =
                "Error in field : " + fieldError.getField() + " " + fieldError.getDefaultMessage();
        exceptionResponse.setMessage(message);

        return ResponseEntity.badRequest().body(exceptionResponse);

    }
}
