package com.example.eshragh.model.entities.user;

import com.example.eshragh.model.entities.BaseEntity;
import com.example.eshragh.service.user.RoleService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity extends BaseEntity implements UserDetails {

    private String username;
    @JsonIgnore
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleEntity roleEntity;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Privileges privilege : roleEntity.getPrivileges()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" +privilege.getId()));
        }
        return authorities;
//        return List.of(new SimpleGrantedAuthority("ROLE_Admin"));
//        return List.of(new SimpleGrantedAuthority("ROLE_" + roleEntity.getId()));
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
