package com.example.eshragh.model.srv;

import com.example.eshragh.model.entities.PortEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PortSrv {
    private Long id;

    private Integer portNumber;
    private PortEntity connectedPort;

}
