package com.example.eshragh.rest;

import com.example.eshragh.model.dtos.SwitchDto;
import com.example.eshragh.model.entities.SwitchEntity;
import com.example.eshragh.model.srv.SwitchSrv;
import com.example.eshragh.repository.SwitchRepository;
import com.example.eshragh.service.SwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/switch")
public class SwitchController extends AbstractController<SwitchEntity, SwitchDto, SwitchSrv, SwitchService> {
    @Autowired
    SwitchRepository switchRepository;
    @PostMapping("/create")

    public SwitchSrv create(@RequestBody SwitchDto switchDto) throws Exception {

        Optional.ofNullable(convertor.convertToEntity(switchDto))
                .filter(entity -> entity.getSwitchName() != null && entity.getUnits() != null)
                .orElseThrow(() -> new Exception("Entering data is necessary."));
        return convertor.convertToSrv(service.add(convertor.convertToEntity(switchDto)));
    }

    @GetMapping("/get/{id}")
    public SwitchSrv findById(@PathVariable Long id) throws Exception {

//        if (this.findById(id) == null) {
//            throw new Exception("IdNotFound");
//        }
        switchRepository.findById(id)
                .orElseThrow(() -> new Exception("server does not exist with id: " + id));

        return convertor.convertToSrv(service.read(id));
    }
    @GetMapping("/getAll")
    public List<SwitchSrv> getAllServer() {

        return convertor.entityCollectionConvertor(service.getAll());
    }
    @GetMapping("/getAllWithPagination")
    public List<SwitchSrv> getAllEntitiesWithPagination(@RequestParam("pageIndex") int pageIndex) {

        return convertor.entityCollectionConvertor(service.getAllWithPagination(pageIndex));
    }

    @PutMapping("/{id}")
    public SwitchSrv update(@PathVariable Long id, @RequestBody SwitchDto switchDto) throws Exception {

        if (this.findById(id) == null) {
            throw new Exception("datacenter not fount.");
        }
        return convertor.convertToSrv(service.updateDatacenter(id,convertor.convertToEntity(switchDto)));
    }
    @DeleteMapping("/{id}")
    public String deleteRack(@PathVariable Long id)  {
        service.deleteById(id);
        return "deleted server with :" +id;
    }
}
