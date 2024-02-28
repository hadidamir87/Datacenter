package com.example.eshragh.model.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
public class RackDto {
    private String rackName;
    private Integer units;
    private List<SwitchDto> switches;
    private List<ServerDto> servers;
    private DatacenterDto dataCenter;

}
