package com.example.eshragh.model.dtos.user;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
public class UserDto {
    private String username;
    private String password;
    private RoleDto roleId;

}
