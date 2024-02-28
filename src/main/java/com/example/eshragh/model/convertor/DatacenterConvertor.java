package com.example.eshragh.model.convertor;

import com.example.eshragh.model.dtos.DatacenterDto;
import com.example.eshragh.model.entities.DatacenterEntity;
import com.example.eshragh.model.srv.DatacenterSrv;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface DatacenterConvertor extends AbstractConvertor<DatacenterEntity, DatacenterDto, DatacenterSrv>{
}
