package com.example.eshragh.model.entities;

import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Version
    private Integer version;
    @CreationTimestamp
    private Date insertTimestamp;
    @UpdateTimestamp
    private Date lastUpdateTimestamp;
}
