package com.example.eshragh.service;
import com.example.eshragh.model.entities.RackEntity;
import com.example.eshragh.repository.RackRepository;
import org.springframework.stereotype.Service;

@Service
public class RackService extends AbstractService<RackEntity, RackRepository>{
    /*@Override
    public RackEntity add(RackEntity rackEntity) throws Exception {
        if (rackEntity.getRackName()==null){
            throw new
        }
        return super.add(rackEntity);
    }

    @Override
    public RackEntity read(Long id) {
        return super.read(id);
    }*/
}
