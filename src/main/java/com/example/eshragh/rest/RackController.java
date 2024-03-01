package com.example.eshragh.rest;


import com.example.eshragh.model.dtos.RackDto;
import com.example.eshragh.model.entities.RackEntity;
import com.example.eshragh.model.srv.RackSrv;
import com.example.eshragh.repository.PortRepository;
import com.example.eshragh.repository.RackRepository;
import com.example.eshragh.service.RackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rack")
public class RackController extends AbstractController<RackEntity, RackDto, RackSrv, RackService> {
    @Autowired
    RackRepository rackRepository;
    @PostMapping("/create")

    public RackSrv create(@RequestBody RackDto rackDto) throws Exception {

        /*if (convertor.convertToEntity(rackDto).getRackName() == null || convertor.convertToEntity(rackDto).getUnits() == null) {
            throw new Exception("entering data is necessary.");
        }*/
        Optional.ofNullable(convertor.convertToEntity(rackDto))
                .filter(entity -> entity.getRackName() != null && entity.getUnits() != null)
                .orElseThrow(() -> new Exception("Entering data is necessary."));
        return convertor.convertToSrv(service.add(convertor.convertToEntity(rackDto)));
    }

    @GetMapping("/get/{id}")
    public RackSrv findById(@PathVariable Long id) throws Exception {

        rackRepository.findById(id)
                .orElseThrow(() -> new Exception("datacenter does not exist with id: " + id));
        return convertor.convertToSrv(service.read(id));
    }
    @GetMapping("/getAll")
    public List<RackSrv> getAllRack() {

        return convertor.entityCollectionConvertor(service.getAll());
    }
    @GetMapping("/getAllWithPagination")
    public List<RackSrv> getAllEntitiesWithPagination(@RequestParam("pageIndex") int pageIndex) {

        return convertor.entityCollectionConvertor(service.getAllWithPagination(pageIndex));
    }
    /*@PutMapping("/{id}")
    public ResponseEntity<RackSrv> update(@PathVariable Long id, @RequestBody RackDto rackDto) {
//        Dto updatedDTO = service.update(id, rackDTO);
// ResponseEntity.ok(updatedRackDTO);
        return null;
    }*/

    @PutMapping("/{id}")
    public RackSrv update(@PathVariable Long id, @RequestBody RackDto rackDto) throws Exception {

        if (this.findById(id) == null) {
            throw new Exception("server not fount.");
        }
        return convertor.convertToSrv(service.updateDatacenter(id,convertor.convertToEntity(rackDto)));
    }
    @DeleteMapping("/{id}")
    public String deleteRack(@PathVariable Long id)  {
        service.deleteById(id);
        return "deleted rack with :" +id;
    }
}
