package com.example.eshragh.model.dtos.user;


import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
public class RoleDto {
    private String title;
    private List<PrivilegesDto> privilegesId;
    private UserDto userId;
}
