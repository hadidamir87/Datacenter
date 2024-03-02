package com.example.eshragh.model.convertor;

import com.example.eshragh.model.dtos.ServerDto;
import com.example.eshragh.model.entities.ServerEntity;
import com.example.eshragh.model.srv.ServerSrv;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ServerConvertor  extends AbstractConvertor<ServerEntity, ServerDto, ServerSrv> {

}
