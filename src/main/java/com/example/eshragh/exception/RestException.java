package com.example.eshragh.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice

public class RestException {
   /* @ExceptionHandler(CustomServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseForClient> getException(CustomServiceException serviceException) {
        ResponseForClient exceptionResponse = new ResponseForClient();
        exceptionResponse.setError(true);
        exceptionResponse.setMessage(serviceException.getErrorCode());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage() );
    }
}
