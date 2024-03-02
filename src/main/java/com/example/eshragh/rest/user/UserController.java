package com.example.eshragh.rest.user;



import com.example.eshragh.model.dtos.user.UserDto;
import com.example.eshragh.model.entities.user.UserEntity;
import com.example.eshragh.model.srv.user.UserSrv;
import com.example.eshragh.repository.user.UserRepository;
import com.example.eshragh.rest.AbstractController;
import com.example.eshragh.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractController<UserEntity, UserDto, UserSrv, UserService> {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/create")
    public UserSrv create(@RequestBody UserDto userDto) throws Exception {


        Optional.ofNullable(convertor.convertToEntity(userDto))
                .filter(entity -> entity.getUsername() != null )
                .orElseThrow(() -> new Exception("Entering data is necessary."));

        return convertor.convertToSrv(service.add(convertor.convertToEntity(userDto)));
    }

    @GetMapping("/get/{id}")
    public UserSrv findById(@PathVariable Long id) throws Exception {

        userRepository.findById(id)
                .orElseThrow(() -> new Exception("user does not exist with id: " + id));

        return convertor.convertToSrv(service.read(id));
    }
    @GetMapping("/getAll")
    public List<UserSrv> getAllPort() {

        return convertor.entityCollectionConvertor(service.getAll());
    }
    @GetMapping("/getAllWithPagination")
    public List<UserSrv> getAllEntitiesWithPagination(@RequestParam("pageIndex") int pageIndex) {

        return convertor.entityCollectionConvertor(service.getAllWithPagination(pageIndex));
    }

    @PutMapping("/{id}")
    public UserSrv update(@PathVariable Long id, @RequestBody UserDto userDto) throws Exception {

        if (this.findById(id) == null) {
            throw new Exception("user not fount.");
        }
        return convertor.convertToSrv(service.updateUser(id,convertor.convertToEntity(userDto)));
    }
    @DeleteMapping("/{id}")
    public String deleteDatacenter(@PathVariable Long id)  {
        service.deleteById(id);
        return "deleted datacenter with :" +id;
    }
}
