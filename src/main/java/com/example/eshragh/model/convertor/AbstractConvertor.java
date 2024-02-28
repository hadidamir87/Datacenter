package com.example.eshragh.model.convertor;

import org.hibernate.service.spi.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

public interface AbstractConvertor<E, Dto, Srv> {

    E convertToEntity(Dto entity);

    Srv convertToSrv(E entity);

    default List<E> dtoCollectionConvertor(List<Dto> dList) {

        if (dList != null) {
            return dList.stream().map(i -> {
                try {
                    return convertToEntity(i);
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());

        }
        return null;
    }

    default List<Srv> entityCollectionConvertor(List<E> eList) {
        if (eList != null){
            return eList.stream().map(i->{
                try{
                    return convertToSrv(i);
                }catch (ServiceException e){
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
        }
        return null;
    }
}
