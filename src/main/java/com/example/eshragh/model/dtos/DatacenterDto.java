package com.example.eshragh.model.dtos;

import com.example.eshragh.model.entities.RackEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
public class DatacenterDto {

    private String datacenterName;
    private Integer units;
    private List<RackDto> racks;

}
