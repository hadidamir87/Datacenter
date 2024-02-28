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
public class ServerEntity extends BaseEntity{

    private String serverName;

    private Integer units;

    @ManyToOne
    @JoinColumn(name = "rack_id")
    private RackEntity rack;

    @OneToMany(mappedBy = "server")
    private List<PortEntity> ports;
}
