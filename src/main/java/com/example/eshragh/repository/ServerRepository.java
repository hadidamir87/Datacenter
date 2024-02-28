package com.example.eshragh.repository;

import com.example.eshragh.model.entities.ServerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<ServerEntity,Long> {
}
