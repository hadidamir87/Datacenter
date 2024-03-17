package com.example.eshragh.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class RackEntity extends BaseEntity{

    private String rackName;
    private Integer units;

    @ManyToOne
    @JoinColumn(name = "datacenter_id")
    private DataCenterEntity dataCenter;

    @OneToMany
    @JoinColumn(name = "server_id")
    private List<ServerEntity> servers;

    @OneToMany
    @JoinColumn(name = "switch_id")
    private List<SwitchEntity> switches;



}
