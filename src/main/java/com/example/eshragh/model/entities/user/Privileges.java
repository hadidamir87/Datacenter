package com.example.eshragh.model.entities.user;

import com.example.eshragh.model.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Privileges extends BaseEntity {
    private String action;
    @ManyToMany
    private List<RoleEntity> roleEntities;
}
