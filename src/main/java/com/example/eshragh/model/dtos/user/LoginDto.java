package com.example.eshragh.model.dtos.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {

    private String username;
    private String password;
}
