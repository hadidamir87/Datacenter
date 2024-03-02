package com.example.eshragh.model.srv.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PrivilegesSrv {
    private Long Id;
    private String action;

}
