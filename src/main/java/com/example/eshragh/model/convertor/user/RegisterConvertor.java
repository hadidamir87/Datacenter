package com.example.eshragh.model.convertor.user;

import com.example.eshragh.model.convertor.AbstractConvertor;

import com.example.eshragh.model.dtos.user.RegisterDto;
import com.example.eshragh.model.entities.user.UserEntity;
import com.example.eshragh.model.srv.user.RegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface RegisterConvertor extends AbstractConvertor<UserEntity, RegisterDto, RegisterResponse> {
}
