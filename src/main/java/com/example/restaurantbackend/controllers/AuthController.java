package com.example.restaurantbackend.controllers;

import com.example.restaurantbackend.domain.user.AuthenticationDTO;
import com.example.restaurantbackend.domain.user.LoginResponseDTO;
import com.example.restaurantbackend.domain.user.RegisterDTO;
import com.example.restaurantbackend.domain.user.User;
import com.example.restaurantbackend.repository.UserRepository;
import com.example.restaurantbackend.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    /**
     * Method to post login
     *
     * @param data
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.name(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/resgister")
    public ResponseEntity register(@RequestBody RegisterDTO data) {
        if (this.userRepository.findByName(data.name()).isPresent()) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        var newUser = User.UserBuilder()
                                        .name(data.name())
                                        .password(encryptedPassword)
                                        .role(data.role());
        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
