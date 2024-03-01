package com.example.eshragh.service;
import com.example.eshragh.model.entities.DatacenterEntity;
import com.example.eshragh.model.entities.RackEntity;
import com.example.eshragh.repository.RackRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RackService extends AbstractService<RackEntity, RackRepository>{

    public RackEntity add(RackEntity rackEntity) throws Exception {
        /*if (rackEntity.getRackName()==null){
            throw new
        }*/
        return repository.save(rackEntity);
    }


    public RackEntity read(Long id) {
        return repository.findById(id).get();
    }

    public RackEntity updateDatacenter(Long id, RackEntity c) throws Exception {

        RackEntity currentRack = repository.findById(id).get();
//            if (c.getId()!=null){
//                throw new Exception();
//            }
        if (c.getRackName() != null) {
            currentRack.setRackName(c.getRackName());
        }
        if (c.getUnits() != null) {
            currentRack.setUnits(c.getUnits());
        }
        return repository.save(currentRack);

    }

    public List<RackEntity> getAll() {

        return repository.findAll();
    }

    public List<RackEntity> getAllWithPagination(int pageNum) {

        return repository.findAll(Pageable.ofSize(2).withPage(pageNum)).getContent();
    }

    public void deleteById(Long id) {
        repository.delete(repository.findById(id).get());

    }

}
