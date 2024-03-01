package com.example.eshragh.model.convertor;


import com.example.eshragh.model.dtos.PortDto;
import com.example.eshragh.model.entities.PortEntity;
import com.example.eshragh.model.srv.PortSrv;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface PortConvertor extends AbstractConvertor<PortEntity, PortDto, PortSrv>{
}
