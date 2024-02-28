package com.example.eshragh.model.srv;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RackSrv {

    private Long id;
    private String rackName;
    private Integer units;
}
