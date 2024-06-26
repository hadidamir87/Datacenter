package com.example.eshragh.model.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataCenterEntity extends BaseEntity {

    private String datacenterName;
    private Integer units;

    @OneToMany(mappedBy = "dataCenter")
    private List<RackEntity> racks;
}
