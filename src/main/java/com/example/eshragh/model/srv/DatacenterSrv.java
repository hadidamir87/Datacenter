package com.example.eshragh.model.srv;

import com.example.eshragh.model.dtos.RackDto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DatacenterSrv {
    private Long id;
    private String datacenterName;
    private Integer units;




}
