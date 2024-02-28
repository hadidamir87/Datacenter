package com.example.eshragh.repository;

import com.example.eshragh.model.entities.DatacenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DatacenterRepository extends JpaRepository<DatacenterEntity, Long> {

}
