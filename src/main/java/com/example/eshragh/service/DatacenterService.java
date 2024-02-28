package com.example.eshragh.service;

import com.example.eshragh.model.entities.DatacenterEntity;
import com.example.eshragh.repository.DatacenterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatacenterService extends AbstractService<DatacenterEntity, DatacenterRepository> {
    @Override
    public DatacenterEntity read(Long id) throws Exception {
        /*repository.findById(id)
                .ifPresentOrElse(
                        datacenterEntity -> {
                            // Entity found, do something with it
                        },
                        () -> {
                            throw new EntityNotFoundException("DatacenterEntity not found with id: " + id);
                        }
                );*/

        return super.read(id);
    }

    /*public DatacenterEntity read(Long id){
            Optional<DatacenterEntity> datacenterOptional = repository.findById(id);
            return datacenterOptional.orElse(null);
        }*/
    public DatacenterEntity updateDatacenter(Long id,DatacenterEntity c) throws Exception {

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
}
