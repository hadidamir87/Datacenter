package com.example.eshragh.model.dtos.user;

import com.example.eshragh.model.entities.user.RoleEntity;
import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private RoleEntity role;
}
