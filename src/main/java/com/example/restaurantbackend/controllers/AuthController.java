package com.example.restaurantbackend.controllers;

import com.example.restaurantbackend.domain.DTO.userDTO.AuthenticationDTO;
import com.example.restaurantbackend.domain.DTO.userDTO.LoginResponseDTO;
import com.example.restaurantbackend.domain.DTO.userDTO.RegisterDTO;
import com.example.restaurantbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
public class AuthController {


    private final UserService userService;

    /**
     * Constructor AuthController
     *
     * @param userService UserService
     */
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method to post login
     *
     * @param data AuthenticationDTO
     * @return ResponseEntity
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
        String token = userService.getToken(data);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    /**
     * Method to post register
     *
     * @param data RegisterDTO
     * @return ResponseEntity
     */
    @PostMapping("/register")
    public ResponseEntity<BCryptPasswordEncoder> register(@RequestBody RegisterDTO data) {

        if (Objects.equals(userService.createUser(data), "badRequest")) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().build();
    }
}
