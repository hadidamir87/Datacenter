package com.example.eshragh.service.user;

import com.example.eshragh.model.entities.DatacenterEntity;
import com.example.eshragh.model.entities.PortEntity;
import com.example.eshragh.model.entities.user.UserEntity;
import com.example.eshragh.repository.DatacenterRepository;
import com.example.eshragh.repository.user.UserRepository;
import com.example.eshragh.service.AbstractService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends AbstractService<UserEntity, UserRepository> {
    public UserEntity add(UserEntity userEntity) {
        return repository.save(userEntity);
    }

    public UserEntity read(Long id) throws Exception {

        return repository.findById(id).get();
    }


    public UserEntity updateUser(Long id, UserEntity c) throws Exception {

        UserEntity currentUser = repository.findById(id).get();
//            if (c.getId()!=null){
//                throw new Exception();
//            }
        if (c.getUsername() != null) {
            currentUser.setUsername(c.getUsername());
        }
        if (c.getPassword() != null) {
            currentUser.setPassword(c.getPassword());
        }
        return repository.save(currentUser);

    }

    public List<UserEntity> getAll() {

        return repository.findAll();
    }

    public List<UserEntity> getAllWithPagination(int pageNum) {

        return repository.findAll(Pageable.ofSize(2).withPage(pageNum)).getContent();
    }

    public void deleteById(Long id) {
        repository.delete(repository.findById(id).get());

    }
}
