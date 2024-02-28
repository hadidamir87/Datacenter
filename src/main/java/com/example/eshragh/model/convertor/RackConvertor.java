package com.example.eshragh.model.convertor;
import com.example.eshragh.model.dtos.RackDto;
import com.example.eshragh.model.entities.RackEntity;
import com.example.eshragh.model.srv.RackSrv;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface RackConvertor extends AbstractConvertor<RackEntity, RackDto, RackSrv> {
}
