package com.example.eshragh.rest;

import com.example.eshragh.model.convertor.AbstractConvertor;
import com.example.eshragh.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;


public class AbstractController<E, Dto, Srv, S extends AbstractService<E, ? extends JpaRepository<E, Long>>> {
    @Autowired
    protected S service;

    @Autowired
    protected AbstractConvertor<E, Dto, Srv> convertor;






}
