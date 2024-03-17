package com.example.eshragh.repository.user;

import com.example.eshragh.model.entities.user.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
