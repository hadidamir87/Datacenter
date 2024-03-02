package com.example.eshragh.rest.user;

import com.example.eshragh.model.dtos.PortDto;
import com.example.eshragh.model.dtos.user.PrivilegesDto;
import com.example.eshragh.model.entities.user.Privileges;
import com.example.eshragh.model.srv.PortSrv;
import com.example.eshragh.model.srv.user.PrivilegesSrv;
import com.example.eshragh.repository.PortRepository;
import com.example.eshragh.rest.AbstractController;
import com.example.eshragh.service.user.PrivilegesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/privileges")
public class PrivilegesController extends AbstractController<Privileges, PrivilegesDto, PrivilegesSrv, PrivilegesService> {
    @Autowired
    PortRepository portRepository;
    @PostMapping("/create")
    public PrivilegesSrv create(@RequestBody PrivilegesDto privilegesDto) throws Exception {


        Optional.ofNullable(convertor.convertToEntity(privilegesDto))
                .filter(entity -> entity.getAction() != null )
                .orElseThrow(() -> new Exception("Entering data is necessary."));

        return convertor.convertToSrv(service.add(convertor.convertToEntity(privilegesDto)));
    }

    @GetMapping("/get/{id}")
    public PrivilegesSrv findById(@PathVariable Long id) throws Exception {

        portRepository.findById(id)
                .orElseThrow(() -> new Exception("datacenter does not exist with id: " + id));

        return convertor.convertToSrv(service.read(id));
    }
    @GetMapping("/getAll")
    public List<PrivilegesSrv> getAllPort() {

        return convertor.entityCollectionConvertor(service.getAll());
    }
    @GetMapping("/getAllWithPagination")
    public List<PrivilegesSrv> getAllEntitiesWithPagination(@RequestParam("pageIndex") int pageIndex) {

        return convertor.entityCollectionConvertor(service.getAllWithPagination(pageIndex));
    }

    @PutMapping("/{id}")
    public PrivilegesSrv update(@PathVariable Long id, @RequestBody PrivilegesDto privilegesDto) throws Exception {

        if (this.findById(id) == null) {
            throw new Exception("privilege not fount.");
        }
        return convertor.convertToSrv(service.updatePrivileges(id,convertor.convertToEntity(privilegesDto)));
    }
    @DeleteMapping("/{id}")
    public String deleteDatacenter(@PathVariable Long id)  {
        service.deleteById(id);
        return "deleted datacenter with :" +id;
    }
}
