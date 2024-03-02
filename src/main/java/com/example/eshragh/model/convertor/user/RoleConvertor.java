package com.example.eshragh.model.convertor.user;

import com.example.eshragh.model.convertor.AbstractConvertor;
import com.example.eshragh.model.dtos.user.RoleDto;
import com.example.eshragh.model.entities.user.RoleEntity;
import com.example.eshragh.model.srv.user.RoleSrv;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface RoleConvertor extends AbstractConvertor<RoleEntity, RoleDto, RoleSrv> {
}
