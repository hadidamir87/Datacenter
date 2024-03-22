package com.example.eshragh.rest.user;


import com.example.eshragh.aop.exception.CustomServiceException;
import com.example.eshragh.model.dtos.user.UserDto;
import com.example.eshragh.model.entities.user.UserEntity;
import com.example.eshragh.model.srv.user.UserSrv;
import com.example.eshragh.rest.AbstractController;
import com.example.eshragh.service.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractController<UserEntity, UserDto, UserSrv, UserService> {

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('ROLE_1')")
//    @PreAuthorize("hasPermission('ROLE_Admin')")
    public UserSrv findById(@PathVariable Long id) throws CustomServiceException {
        return  convertor.convertToSrv(service.findById(id));
    }


    @PostMapping("/set/user/{roleId}/role/{userId}")
    public UserSrv setUserIntoRole(@PathVariable Long roleId, @PathVariable Long userId) {
        UserEntity userEntity = service.assignRoleToUser(roleId, userId);
        return convertor.convertToSrv(userEntity);
//        return ResponseEntity.ok(roleEntity);
    }
    @GetMapping("/getAll")
//    @PreAuthorize("hasAuthority('ROLE_admin')")

    public List<UserSrv> getAllUser() {
        return convertor.entityCollectionConvertor(service.getAll());
    }

    @GetMapping("/getAllWithPagination")
    public List<UserSrv> getAllUsersWithPagination(@RequestParam("pageIndex") int pageIndex) {
        return convertor.entityCollectionConvertor(service.getAllWithPagination(pageIndex));
    }

    @PutMapping("/{id}")
    public UserSrv update(@PathVariable Long id, @RequestBody UserDto userDto) throws CustomServiceException {
        return convertor.convertToSrv(service.updateUser(id, convertor.convertToEntity(userDto)));
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) throws CustomServiceException {
        service.deleteById(id);
        return "کاربر با شناسه " + id + " حذف شد";
    }
}
