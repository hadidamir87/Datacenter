package com.example.eshragh.service.user;


import com.example.eshragh.model.convertor.user.RoleConvertor;
import com.example.eshragh.model.entities.user.Privileges;
import com.example.eshragh.model.entities.user.RoleEntity;
import com.example.eshragh.model.entities.user.UserEntity;
import com.example.eshragh.model.srv.user.RoleSrv;
import com.example.eshragh.repository.user.RoleRepository;
import com.example.eshragh.repository.user.UserRepository;
import com.example.eshragh.service.AbstractService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleService extends AbstractService<RoleEntity, RoleRepository> {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleConvertor roleConvertor;
    @Autowired
    PrivilegesService privilegesService;

    public RoleEntity add(RoleEntity roleEntity) {
        roleEntity.setUserEntity(roleEntity.getUserEntity());
        return repository.save(roleEntity);
    }

    public RoleEntity read(Long id) throws Exception {

        return repository.findById(id).get();
    }


    public RoleEntity updateRole(Long id, RoleEntity c) throws Exception {

        RoleEntity currentRole = repository.findById(id).get();
//            if (c.getId()!=null){
//                throw new Exception();
//            }
        if (c.getTitle() != null) {
            currentRole.setTitle(c.getTitle());
        }
        if (c.getPrivileges() != null) {
            currentRole.setPrivileges(c.getPrivileges());
        }

        return repository.save(currentRole);

    }


    public List<RoleEntity> getAll() {

        return repository.findAll();
    }


    @Autowired
    private RoleRepository roleRepository;

    public List<Long> getPrivilegeIdsByRoleId(Long roleId) {
        RoleEntity role = roleRepository.findById(roleId).orElse(null);
        if (role != null) {
            return role.getPrivileges().stream()
                    .map(Privileges::getId)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
   /* public List<Long> getAllPrivilegesByRoleId(Long roleId) {
        Set<Long> privilegesId = new HashSet<>();
        List<Long> listOfRoles = this.getAll().;

        } (this.getAll().iterator())
            return repository.findAll();
    }*/

    public List<RoleEntity> getAllWithPagination(int pageNum) {

        return repository.findAll(Pageable.ofSize(2).withPage(pageNum)).getContent();
    }

    public void deleteById(Long id) {
        repository.delete(repository.findById(id).get());

    }

    public RoleSrv assignPrivilegesToRole(Long roleId, List<Privileges> privilegesId) throws Exception {
        try {
            if (privilegesId == null)
                System.err.printf("privileges not found " + privilegesId);
        } catch (NullPointerException e) {
            System.err.printf("error " + e.getMessage());
        }
        try {
            for (Privileges pId : privilegesId) {
                if (privilegesService.read(pId.getId()) == null) {
                    System.err.printf("id of privileges not found " + privilegesId);
                }
            }
        } catch (NullPointerException e) {
            System.err.printf("error " + e.getMessage());

        }
        try {
            if (roleId == null)
                System.err.printf("id must be enter " + roleId);
        } catch (NullPointerException e) {
            System.err.printf("error " + e.getMessage());
        }
        try {
            if (this.read(roleId) == null)
                System.err.printf("role not found " + roleId);
        } catch (NullPointerException e) {
            System.err.printf("error " + e.getMessage());
        }
        List<Privileges> privilegesList = new ArrayList<>();
        for (Privileges pId : privilegesId) {
            privilegesList.add(privilegesService.read(pId.getId()));
        }
        RoleEntity roleEntity = this.read(roleId);
        roleEntity.setPrivileges(privilegesList);
        this.add(roleEntity);
        return roleConvertor.convertToSrv(roleEntity);
    }

    public RoleEntity assignUserToRole(Long roleId, Long UserId) {
        RoleEntity roleEntity = repository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("role not found"));

        UserEntity userEntity = userRepository.findById(UserId)
                .orElseThrow(() -> new EntityNotFoundException("user-not-found"));
        roleEntity.setUserEntity(userEntity);
        return repository.save(roleEntity);
    }

}
