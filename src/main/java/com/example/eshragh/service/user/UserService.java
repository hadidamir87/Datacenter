package com.example.eshragh.service.user;

import com.example.eshragh.configuration.jwt.JwtService;
import com.example.eshragh.exception.CustomServiceException;
import com.example.eshragh.model.dtos.user.LoginDto;
import com.example.eshragh.model.entities.user.RoleEntity;
import com.example.eshragh.model.entities.user.UserEntity;
import com.example.eshragh.model.srv.user.AuthenticationResponse;
import com.example.eshragh.model.srv.user.UserSrv;
import com.example.eshragh.repository.user.RoleRepository;
import com.example.eshragh.repository.user.UserRepository;
import com.example.eshragh.service.AbstractService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor

public class UserService extends AbstractService<UserEntity, UserRepository> implements UserDetailsService {


    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;

    public UserEntity findById(Long id) throws CustomServiceException {
        return repository.findById(id)
                .orElseThrow(() -> new CustomServiceException("user-not-found"));
    }

    public UserEntity updateUser(Long id, UserEntity c) throws CustomServiceException {

        UserEntity currentUser = findById(id);

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

    public void deleteById(Long id) throws CustomServiceException {
        UserEntity user = findById(id);
        repository.delete(user);

    }
    public UserEntity assignRoleToUser(Long roleId, Long userId) {
        UserEntity userEntity = repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));

        RoleEntity roleEntity = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("role-not-found"));
        userEntity.setRoleEntity(roleEntity);
        return repository.save(userEntity);
    }


    public UserEntity register(UserEntity user) throws Exception {
        if (repository.existsByUsername(user.getUsername())) {
            throw new CustomServiceException("username-duplicated");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoleEntity(user.getRoleEntity());
        repository.save(user);
        return user;
    }

    public AuthenticationResponse login(LoginDto authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtService.generateToken(userDetails);
        return AuthenticationResponse.builder().token(token).build();

    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new CustomServiceException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            throw new CustomServiceException("INVALID_CREDENTIALS");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow();
    }
}
