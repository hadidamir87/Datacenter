package com.example.eshragh.model.dtos.user;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
public class PrivilegesDto {
    private String action;
//    private Set<RoleDto> roleId;

}
