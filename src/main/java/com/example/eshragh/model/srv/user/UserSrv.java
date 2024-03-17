package com.example.eshragh.model.srv.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
//@NoArgsConstructor
@Builder
@Data
public class UserSrv  {
    private String username;
    private final String jwtToken;

    public UserSrv(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String UserSrv() {
        return this.jwtToken;
    }
}
