package com.example.eshragh.rest;

import com.example.eshragh.model.dtos.DatacenterDto;
import com.example.eshragh.model.entities.DatacenterEntity;
import com.example.eshragh.model.srv.DatacenterSrv;
import com.example.eshragh.repository.DatacenterRepository;
import com.example.eshragh.service.DatacenterService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/datacenter")
public class DatacenterController extends AbstractController<DatacenterEntity, DatacenterDto, DatacenterSrv, DatacenterService> {
    @Autowired
    DatacenterRepository datacenterRepository;

    @PostMapping("/create")

    public DatacenterSrv create(@RequestBody DatacenterDto datacenterDto) throws Exception {

        if (convertor.convertToEntity(datacenterDto).getDatacenterName() == null||convertor.convertToEntity(datacenterDto).getUnits() == null) {
            throw new Exception("entering data is necessary.");
        }
        return convertor.convertToSrv(service.add(convertor.convertToEntity(datacenterDto)));
    }

    @GetMapping("/get/{id}")
    public DatacenterSrv findById(@PathVariable Long id) throws Exception {
        datacenterRepository.findById(id)
                .ifPresentOrElse(
                        datacenterEntity -> {
                            // Entity found, do something with it
                        },
                        () -> {
                            throw new EntityNotFoundException("datacenter does not exist with id: " + id);
                        }
                );

        return convertor.convertToSrv(service.read(id));
    }

    @PutMapping("/{id}")
    public DatacenterSrv update(@PathVariable Long id, @RequestBody DatacenterDto datacenterDto) throws Exception {


        if (this.findById(id) == null) {
            throw new Exception("datacenter not fount.");
        }

        return convertor.convertToSrv(service.updateDatacenter(id,convertor.convertToEntity(datacenterDto)));
    }
}
