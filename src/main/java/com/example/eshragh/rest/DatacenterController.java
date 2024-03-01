package com.example.eshragh.rest;

import com.example.eshragh.model.dtos.DatacenterDto;
import com.example.eshragh.model.entities.DatacenterEntity;
import com.example.eshragh.model.srv.DatacenterSrv;
import com.example.eshragh.repository.DatacenterRepository;
import com.example.eshragh.service.DatacenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/datacenter")
public class DatacenterController extends AbstractController<DatacenterEntity, DatacenterDto, DatacenterSrv, DatacenterService> {
    @Autowired
    DatacenterRepository datacenterRepository;

    @PostMapping("/create")
    public DatacenterSrv create(@RequestBody DatacenterDto datacenterDto) throws Exception {

        /*if (convertor.convertToEntity(datacenterDto).getDatacenterName() == null||
                convertor.convertToEntity(datacenterDto).getUnits() == null) {
            throw new Exception("entering data is necessary.");
        }*/
        Optional.ofNullable(convertor.convertToEntity(datacenterDto))
                .filter(entity -> entity.getDatacenterName() != null && entity.getUnits() != null)
                .orElseThrow(() -> new Exception("Entering data is necessary."));

        return convertor.convertToSrv(service.add(convertor.convertToEntity(datacenterDto)));
    }

    @GetMapping("/get/{id}")
    public DatacenterSrv findById(@PathVariable Long id) throws Exception {

        datacenterRepository.findById(id)
                .orElseThrow(() -> new Exception("datacenter does not exist with id: " + id));

        return convertor.convertToSrv(service.read(id));
    }
    @GetMapping("/getAll")
    public List<DatacenterSrv> getAllDatacenter() {

        return convertor.entityCollectionConvertor(service.getAll());
    }
    @GetMapping("/getAllWithPagination")
    public List<DatacenterSrv> getAllEntitiesWithPagination(@RequestParam("pageIndex") int pageIndex) {

        return convertor.entityCollectionConvertor(service.getAllWithPagination(pageIndex));
    }

    @PutMapping("/{id}")
    public DatacenterSrv update(@PathVariable Long id, @RequestBody DatacenterDto datacenterDto) throws Exception {

        if (this.findById(id) == null) {
            throw new Exception("datacenter not fount.");
        }
        return convertor.convertToSrv(service.updateDatacenter(id,convertor.convertToEntity(datacenterDto)));
    }
    @DeleteMapping("/{id}")
    public String deleteDatacenter(@PathVariable Long id)  {
        service.deleteById(id);
        return "deleted datacenter with :" +id;
    }
}
