package com.example.eshragh.exception.handedOfExceptions;

import lombok.Data;

@Data
public class ResponseForClient {
    private String message;
    private Boolean error;
}
