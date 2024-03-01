package com.example.eshragh.model.convertor;

import com.example.eshragh.model.dtos.RackDto;
import com.example.eshragh.model.dtos.SwitchDto;
import com.example.eshragh.model.entities.RackEntity;
import com.example.eshragh.model.entities.SwitchEntity;
import com.example.eshragh.model.srv.RackSrv;
import com.example.eshragh.model.srv.SwitchSrv;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface SwitchConvertor  extends AbstractConvertor<SwitchEntity, SwitchDto, SwitchSrv> {
}
