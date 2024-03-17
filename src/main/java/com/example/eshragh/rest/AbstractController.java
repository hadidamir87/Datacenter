package com.example.eshragh.rest;

import com.example.eshragh.model.convertor.AbstractConvertor;
import com.example.eshragh.model.srv.DatacenterSrv;
import com.example.eshragh.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


public class AbstractController<E, Dto, Srv, S extends AbstractService<E, ? extends JpaRepository<E, Long>>> {
    @Autowired
    protected S service;

    @Autowired
    protected AbstractConvertor<E, Dto, Srv> convertor;






}
