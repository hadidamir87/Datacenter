package com.example.eshragh.service.user;

import com.example.eshragh.model.entities.PortEntity;
import com.example.eshragh.model.entities.user.Privileges;
import com.example.eshragh.repository.user.PrivilegesRepository;
import com.example.eshragh.service.AbstractService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegesService extends AbstractService<Privileges, PrivilegesRepository> {
    public Privileges add(Privileges privileges) {
        return repository.save(privileges);
    }

    public Privileges read(Long id) throws Exception {

        return repository.findById(id).get();
    }


    public Privileges updatePrivileges(Long id, Privileges c) throws Exception {

        Privileges currentPrivileges = repository.findById(id).get();
//            if (c.getId()!=null){
//                throw new Exception();
//            }
        if (c.getAction() != null) {
            currentPrivileges.setAction(c.getAction());
        }

        return repository.save(currentPrivileges);

    }

    public List<Privileges> getAll() {

        return repository.findAll();
    }

    public List<Privileges> getAllWithPagination(int pageNum) {

        return repository.findAll(Pageable.ofSize(2).withPage(pageNum)).getContent();
    }

    public void deleteById(Long id) {
        repository.delete(repository.findById(id).get());

    }
}
