package com.example.eshragh.service;

import com.example.eshragh.model.entities.DataCenterEntity;
import com.example.eshragh.repository.DatacenterRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatacenterService extends AbstractService<DataCenterEntity, DatacenterRepository> {
    public DataCenterEntity add(DataCenterEntity datacenterEntity) {
        return repository.save(datacenterEntity);
    }

    public DataCenterEntity read(Long id) throws Exception {

        return repository.findById(id).get();
    }


    public DataCenterEntity updateDatacenter(Long id, DataCenterEntity c) throws Exception {

        DataCenterEntity currentDatacenter = repository.findById(id).get();
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

    public List<DataCenterEntity> getAll() {

        return repository.findAll();
    }

    public List<DataCenterEntity> getAllWithPagination(int pageNum) {

        return repository.findAll(Pageable.ofSize(2).withPage(pageNum)).getContent();
    }

    public void deleteById(Long id) {
        repository.delete(repository.findById(id).get());

    }
}
