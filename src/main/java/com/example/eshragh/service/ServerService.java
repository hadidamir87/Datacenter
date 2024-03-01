package com.example.eshragh.service;


import com.example.eshragh.model.entities.RackEntity;
import com.example.eshragh.model.entities.ServerEntity;
import com.example.eshragh.repository.ServerRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ServerService extends AbstractService<ServerEntity, ServerRepository>{
    public ServerEntity add(ServerEntity serverEntity) throws Exception {
        /*if (rackEntity.getRackName()==null){
            throw new
        }*/
        return repository.save(serverEntity);
    }


    public ServerEntity read(Long id) {
        return repository.findById(id).get();
    }

    public ServerEntity updateDatacenter(Long id, ServerEntity c) throws Exception {

        ServerEntity currentServer = repository.findById(id).get();
//            if (c.getId()!=null){
//                throw new Exception();
//            }
        if (c.getServerName() != null) {
            currentServer.setServerName(c.getServerName());
        }
        if (c.getUnits() != null) {
            currentServer.setUnits(c.getUnits());
        }
        return repository.save(currentServer);

    }

    public List<ServerEntity> getAll() {

        return repository.findAll();
    }

    public List<ServerEntity> getAllWithPagination(int pageNum) {

        return repository.findAll(Pageable.ofSize(2).withPage(pageNum)).getContent();
    }

    public void deleteById(Long id) {
        repository.delete(repository.findById(id).get());

    }
}
