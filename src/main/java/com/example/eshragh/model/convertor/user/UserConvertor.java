package com.example.eshragh.model.convertor.user;

import com.example.eshragh.model.convertor.AbstractConvertor;
import com.example.eshragh.model.dtos.user.UserDto;
import com.example.eshragh.model.entities.user.UserEntity;
import com.example.eshragh.model.srv.user.UserSrv;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserConvertor extends AbstractConvertor<UserEntity, UserDto, UserSrv> {
}
