package com.example.eshragh.service;


import com.example.eshragh.model.entities.PortEntity;
import com.example.eshragh.repository.PortRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class PortService extends AbstractService<PortEntity, PortRepository> {
    public PortEntity add(PortEntity portEntity) {
        return repository.save(portEntity);
    }

    public PortEntity read(Long id) throws Exception {

        return repository.findById(id).get();
    }


    public PortEntity updatePort(Long id, PortEntity c) throws Exception {

        PortEntity currentPort = repository.findById(id).get();
//            if (c.getId()!=null){
//                throw new Exception();
//            }
        if (c.getPortNumber() != null) {
            currentPort.setPortNumber(c.getPortNumber());
        }
        if (c.getConnectedPort() != null) {
            currentPort.setConnectedPort(c.getConnectedPort());
        }
        return repository.save(currentPort);

    }

    public List<PortEntity> getAll() {

        return repository.findAll();
    }

    public List<PortEntity> getAllWithPagination(int pageNum) {

        return repository.findAll(Pageable.ofSize(2).withPage(pageNum)).getContent();
    }

    public void deleteById(Long id) {
        repository.delete(repository.findById(id).get());

    }
}
