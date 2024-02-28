package com.example.eshragh.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class PortEntity extends BaseEntity{

    private Integer portNumber;


    @OneToOne
    @JoinColumn(name = "connected_port")
    private PortEntity connectedPort;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private ServerEntity server;

    @ManyToOne
    @JoinColumn(name = "switch_id")
    private SwitchEntity switchEntity;


}
