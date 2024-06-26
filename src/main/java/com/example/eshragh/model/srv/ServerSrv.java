package com.example.eshragh.model.srv;

import com.example.eshragh.model.entities.RackEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ServerSrv {
    private Long id;

    private String serverName;
    private Integer units;
    private RackEntity rack;

}
