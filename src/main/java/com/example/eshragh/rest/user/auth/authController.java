package com.example.eshragh.rest.user.auth;

import com.example.eshragh.model.dtos.user.LoginDto;
import com.example.eshragh.model.dtos.user.RegisterDto;
import com.example.eshragh.model.entities.user.UserEntity;
import com.example.eshragh.model.srv.user.AuthenticationResponse;
import com.example.eshragh.model.srv.user.RegisterResponse;
import com.example.eshragh.rest.AbstractController;
import com.example.eshragh.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class authController extends AbstractController<UserEntity, RegisterDto, RegisterResponse, UserService> {

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@Validated @RequestBody RegisterDto dto) throws Exception {

        return ResponseEntity.ok(service.register(convertor.convertToEntity(dto)));
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginDto loginRequest) throws Exception {
        return ResponseEntity.ok(service.login(loginRequest));
    }
}
