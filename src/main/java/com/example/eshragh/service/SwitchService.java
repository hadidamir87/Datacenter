package com.example.eshragh.service;

import com.example.eshragh.model.entities.ServerEntity;
import com.example.eshragh.model.entities.SwitchEntity;
import com.example.eshragh.repository.SwitchRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SwitchService extends AbstractService<SwitchEntity, SwitchRepository>{
    public SwitchEntity add(SwitchEntity switchEntity) throws Exception {
        /*if (rackEntity.getRackName()==null){
            throw new
        }*/
        return repository.save(switchEntity);
    }


    public SwitchEntity read(Long id) {
        return repository.findById(id).get();
    }

    public SwitchEntity updateDatacenter(Long id, SwitchEntity c) throws Exception {

        SwitchEntity currentSwitch = repository.findById(id).get();
//            if (c.getId()!=null){
//                throw new Exception();
//            }
        if (c.getSwitchName() != null) {
            currentSwitch.setSwitchName(c.getSwitchName());
        }
        if (c.getUnits() != null) {
            currentSwitch.setUnits(c.getUnits());
        }
        return repository.save(currentSwitch);

    }

    public List<SwitchEntity> getAll() {

        return repository.findAll();
    }

    public List<SwitchEntity> getAllWithPagination(int pageNum) {

        return repository.findAll(Pageable.ofSize(2).withPage(pageNum)).getContent();
    }

    public void deleteById(Long id) {
        repository.delete(repository.findById(id).get());

    }
}
