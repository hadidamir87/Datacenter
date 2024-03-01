package com.example.eshragh.model.entities.user;

import com.example.eshragh.model.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class UserEntity extends BaseEntity {
    private String username;
    @JsonIgnore

    private String password;
    @OneToOne
    private RoleEntity roleEntity;
}
