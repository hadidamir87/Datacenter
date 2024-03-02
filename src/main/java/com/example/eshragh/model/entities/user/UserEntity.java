package com.example.eshragh.model.entities.user;

import com.example.eshragh.model.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
public class UserEntity extends BaseEntity {
    private String username;
    @JsonIgnore
    private String password;
    @OneToOne
    private RoleEntity roleEntity;

}
