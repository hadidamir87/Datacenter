package com.example.eshragh.rest;
import com.example.eshragh.model.dtos.DatacenterDto;
import com.example.eshragh.model.dtos.RackDto;
import com.example.eshragh.model.entities.RackEntity;
import com.example.eshragh.model.srv.DatacenterSrv;
import com.example.eshragh.model.srv.RackSrv;
import com.example.eshragh.service.RackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rack")
public class RackController extends AbstractController<RackEntity, RackDto, RackSrv, RackService>{
    @PostMapping("/create")

    public RackSrv create(@RequestBody RackDto rackDto) throws Exception {

        if (convertor.convertToEntity(rackDto).getDataCenter() == null||convertor.convertToEntity(rackDto).getUnits() == null) {
            throw new Exception("entering data is necessary.");
        }
        return convertor.convertToSrv(service.add(convertor.convertToEntity(rackDto)));
    }
    @GetMapping("/get/{id}")
    public RackSrv findById(@PathVariable Long id) throws Exception {

//        if (this.findById(id) == null) {
//            throw new Exception("IdNotFound");
//        }
        return convertor.convertToSrv(service.read(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RackSrv> update(@PathVariable Long id, @RequestBody RackDto rackDto) {
//        Dto updatedDTO = service.update(id, rackDTO);
// ResponseEntity.ok(updatedRackDTO);
        return null;
    }
}
