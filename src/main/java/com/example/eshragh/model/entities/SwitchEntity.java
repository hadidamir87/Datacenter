package com.example.eshragh.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class SwitchEntity extends BaseEntity{

    private String switchName;

    private Integer units;

    @ManyToOne
    @JoinColumn(name = "rack_id")
    private RackEntity rack;

    @OneToMany(mappedBy = "switchEntity")
    private List<PortEntity> ports;
}
