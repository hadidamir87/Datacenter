package com.example.eshragh.rest;

import com.example.eshragh.model.dtos.DatacenterDto;
import com.example.eshragh.model.dtos.PortDto;
import com.example.eshragh.model.entities.PortEntity;
import com.example.eshragh.model.srv.DatacenterSrv;
import com.example.eshragh.model.srv.PortSrv;
import com.example.eshragh.repository.DatacenterRepository;
import com.example.eshragh.repository.PortRepository;
import com.example.eshragh.service.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/port")
public class PortController extends AbstractController<PortEntity, PortDto, PortSrv, PortService> {
    @Autowired
    PortRepository portRepository;
    @PostMapping("/create")
    public PortSrv create(@RequestBody PortDto portDto) throws Exception {


        Optional.ofNullable(convertor.convertToEntity(portDto))
                .filter(entity -> entity.getPortNumber() != null )
                .orElseThrow(() -> new Exception("Entering data is necessary."));

        return convertor.convertToSrv(service.add(convertor.convertToEntity(portDto)));
    }

    @GetMapping("/get/{id}")
    public PortSrv findById(@PathVariable Long id) throws Exception {

        portRepository.findById(id)
                .orElseThrow(() -> new Exception("datacenter does not exist with id: " + id));

        return convertor.convertToSrv(service.read(id));
    }
    @GetMapping("/getAll")
    public List<PortSrv> getAllPort() {

        return convertor.entityCollectionConvertor(service.getAll());
    }
    @GetMapping("/getAllWithPagination")
    public List<PortSrv> getAllEntitiesWithPagination(@RequestParam("pageIndex") int pageIndex) {

        return convertor.entityCollectionConvertor(service.getAllWithPagination(pageIndex));
    }

    @PutMapping("/{id}")
    public PortSrv update(@PathVariable Long id, @RequestBody PortDto portDto) throws Exception {

        if (this.findById(id) == null) {
            throw new Exception("datacenter not fount.");
        }
        return convertor.convertToSrv(service.updatePort(id,convertor.convertToEntity(portDto)));
    }
    @DeleteMapping("/{id}")
    public String deleteDatacenter(@PathVariable Long id)  {
        service.deleteById(id);
        return "deleted datacenter with :" +id;
    }
}
