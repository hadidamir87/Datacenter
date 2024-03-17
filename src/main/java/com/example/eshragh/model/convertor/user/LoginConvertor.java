package com.example.eshragh.model.convertor.user;

import com.example.eshragh.model.convertor.AbstractConvertor;
import com.example.eshragh.model.dtos.user.LoginDto;
import com.example.eshragh.model.entities.user.UserEntity;
import com.example.eshragh.model.srv.user.AuthenticationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginConvertor extends AbstractConvertor<UserEntity, LoginDto, AuthenticationResponse> {
}
