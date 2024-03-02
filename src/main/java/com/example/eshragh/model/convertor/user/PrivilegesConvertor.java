package com.example.eshragh.model.convertor.user;

import com.example.eshragh.model.convertor.AbstractConvertor;
import com.example.eshragh.model.dtos.user.PrivilegesDto;
import com.example.eshragh.model.entities.user.Privileges;
import com.example.eshragh.model.srv.user.PrivilegesSrv;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface PrivilegesConvertor extends AbstractConvertor<Privileges, PrivilegesDto, PrivilegesSrv> {
}
