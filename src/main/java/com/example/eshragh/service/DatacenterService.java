package com.example.eshragh.service;

import com.example.eshragh.model.dtos.DatacenterDto;
import com.example.eshragh.model.entities.DatacenterEntity;
import com.example.eshragh.model.srv.DatacenterSrv;
import com.example.eshragh.repository.DatacenterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatacenterService extends AbstractService<DatacenterEntity, DatacenterRepository> {
    public DatacenterEntity add(DatacenterEntity datacenterEntity) {
        return repository.save(datacenterEntity);
    }

    public DatacenterEntity read(Long id) throws Exception {

        return repository.findById(id).get();
    }


    public DatacenterEntity updateDatacenter(Long id, DatacenterEntity c) throws Exception {

        DatacenterEntity currentDatacenter = repository.findById(id).get();
//            if (c.getId()!=null){
//                throw new Exception();
//            }
        if (c.getDatacenterName() != null) {
            currentDatacenter.setDatacenterName(c.getDatacenterName());
        }
        if (c.getUnits() != null) {
            currentDatacenter.setUnits(c.getUnits());
        }
        return repository.save(currentDatacenter);

    }

    public List<DatacenterEntity> getAll() {

        return repository.findAll();
    }

    public List<DatacenterEntity> getAllWithPagination(int pageNum) {

        return repository.findAll(Pageable.ofSize(2).withPage(pageNum)).getContent();
    }

    public void deleteById(Long id) {
        repository.delete(repository.findById(id).get());

    }
}
