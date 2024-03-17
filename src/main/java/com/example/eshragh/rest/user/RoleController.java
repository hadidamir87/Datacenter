package com.example.eshragh.rest.user;

import com.example.eshragh.model.dtos.user.PrivilegesDto;
import com.example.eshragh.model.dtos.user.RoleDto;
import com.example.eshragh.model.entities.user.Privileges;
import com.example.eshragh.model.entities.user.RoleEntity;
import com.example.eshragh.model.srv.user.RoleSrv;
import com.example.eshragh.repository.user.RoleRepository;
import com.example.eshragh.rest.AbstractController;
import com.example.eshragh.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController extends AbstractController<RoleEntity, RoleDto, RoleSrv, RoleService> {
    @Autowired
    RoleRepository roleRepository;
    @PostMapping("/create")
    public RoleSrv create(@RequestBody RoleDto roleDto) throws Exception {

        Optional.ofNullable(convertor.convertToEntity(roleDto))
                .filter(entity -> entity.getTitle() != null )
                .orElseThrow(() -> new Exception("Entering data is necessary."));

        return convertor.convertToSrv(service.add(convertor.convertToEntity(roleDto)));
    }
    @PostMapping("/set/user/{roleId}/role/{userId}")
    public RoleSrv setUserIntoRole(@PathVariable Long roleId, @PathVariable Long userId) {
        RoleEntity roleEntity = service.assignUserToRole(roleId, userId);
        return convertor.convertToSrv(roleEntity);
//        return ResponseEntity.ok(roleEntity);
    }
   /* @PostMapping("/set/role/{roleId}")
    public RoleSrv setPrivilegesIntoRole(@PathVariable Long roleId, @RequestBody List<PrivilegesDto> privilegesDto) {
        RoleEntity roleEntity = service.assignUserToRole(roleId, userId);
        return convertor.convertToSrv(roleEntity);
//        return ResponseEntity.ok(roleEntity);
    }*/

    @PostMapping("/{roleId}/assign-privileges")
    public ResponseEntity<RoleSrv> assignPrivilegesToRole(@PathVariable Long roleId, @RequestBody List<Privileges> privilegesId) {
        try {
            RoleSrv assignedRole = service.assignPrivilegesToRole(roleId, privilegesId);
            return ResponseEntity.ok(assignedRole);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/get/{id}")
    public RoleSrv findById(@PathVariable Long id) throws Exception {

        roleRepository.findById(id)
                .orElseThrow(() -> new Exception("role does not exist with id: " + id));

        return convertor.convertToSrv(service.read(id));
    }
    @GetMapping("/getAll")
    public List<RoleSrv> getAllRole() {

        return convertor.entityCollectionConvertor(service.getAll());
    }
    @GetMapping("/getAllWithPagination")
    public List<RoleSrv> getAllEntitiesWithPagination(@RequestParam("pageIndex") int pageIndex) {

        return convertor.entityCollectionConvertor(service.getAllWithPagination(pageIndex));
    }

  /*  @Autowired
    private RoleService roleService;*/

    @GetMapping("/roles/{roleId}/privilegeIds")
    public List<Long> getPrivilegeIdsByRoleId(@PathVariable Long roleId) {
        return service.getPrivilegeIdsByRoleId(roleId);
    }

    @PutMapping("/{id}")
    public RoleSrv update(@PathVariable Long id, @RequestBody RoleDto roleDto) throws Exception {

        if (this.findById(id) == null) {
            throw new Exception("role not fount.");
        }
        return convertor.convertToSrv(service.updateRole(id,convertor.convertToEntity(roleDto)));
    }
    @DeleteMapping("/{id}")
    public String deleteDatacenter(@PathVariable Long id)  {
        service.deleteById(id);
        return "deleted role with :" +id;
    }
}
