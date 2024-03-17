package com.example.eshragh.model.entities.user;

import com.example.eshragh.model.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleEntity extends BaseEntity {
    private String title;
    @OneToOne(mappedBy = "roleEntity")
    private UserEntity userEntity;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Privileges> privileges;
}
