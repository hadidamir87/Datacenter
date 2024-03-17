package com.example.eshragh.model.dtos.user;


import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
public class UserDto  implements Serializable{
//
    private String username;
    private String password;
    private RoleDto roleId;

//    private static final long serialVersionUID = 5926468583005150707L;

    //need default constructor for JSON Parsing
 /*   public UserDto()
    {

    }

    public UserDto(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }
*/
   /* public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getToken() {
        return this.jwtToken;
    }*/

}
