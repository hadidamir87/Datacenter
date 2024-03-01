package com.example.eshragh.service;


import com.example.eshragh.model.convertor.AbstractConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public class AbstractService<E, R extends JpaRepository<E, Long>> {
    @Autowired
    R repository;
   /* @Autowired
    AbstractConvertor convertor;*/

  /*  public E add(E e) throws Exception {
        return repository.save(e);
    }

    public E read(Long id) throws Exception {
        return repository.findById(id).get();
    }*/
}
