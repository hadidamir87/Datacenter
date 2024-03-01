package com.example.eshragh.rest;


import com.example.eshragh.model.dtos.ServerDto;
import com.example.eshragh.model.entities.ServerEntity;
import com.example.eshragh.model.srv.ServerSrv;
import com.example.eshragh.repository.DatacenterRepository;
import com.example.eshragh.repository.ServerRepository;
import com.example.eshragh.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/server")
public class ServerController  extends AbstractController<ServerEntity, ServerDto, ServerSrv, ServerService>{
    @Autowired
    ServerRepository serverRepository;
    @PostMapping("/create")

    public ServerSrv create(@RequestBody ServerDto serverDto) throws Exception {

        Optional.ofNullable(convertor.convertToEntity(serverDto))
                .filter(entity -> entity.getServerName() != null && entity.getUnits() != null)
                .orElseThrow(() -> new Exception("Entering data is necessary."));
        return convertor.convertToSrv(service.add(convertor.convertToEntity(serverDto)));
    }

    @GetMapping("/get/{id}")
    public ServerSrv findById(@PathVariable Long id) throws Exception {

//        if (this.findById(id) == null) {
//            throw new Exception("IdNotFound");
//        }
        serverRepository.findById(id)
                .orElseThrow(() -> new Exception("server does not exist with id: " + id));
        return convertor.convertToSrv(service.read(id));
    }
    @GetMapping("/getAll")
    public List<ServerSrv> getAllServer() {

        return convertor.entityCollectionConvertor(service.getAll());
    }
    @GetMapping("/getAllWithPagination")
    public List<ServerSrv> getAllEntitiesWithPagination(@RequestParam("pageIndex") int pageIndex) {

        return convertor.entityCollectionConvertor(service.getAllWithPagination(pageIndex));
    }

    @PutMapping("/{id}")
    public ServerSrv update(@PathVariable Long id, @RequestBody ServerDto serverDto) throws Exception {

        if (this.findById(id) == null) {
            throw new Exception("server not fount.");
        }
        return convertor.convertToSrv(service.updateDatacenter(id,convertor.convertToEntity(serverDto)));
    }
    @DeleteMapping("/{id}")
    public String deleteRack(@PathVariable Long id)  {
        service.deleteById(id);
        return "deleted server with :" +id;
    }
}
