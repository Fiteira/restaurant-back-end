package com.example.restaurantbackend.service;

import com.example.restaurantbackend.domain.DTO.userDTO.AuthenticationDTO;
import com.example.restaurantbackend.domain.DTO.userDTO.RegisterDTO;
import com.example.restaurantbackend.domain.user.User;
import com.example.restaurantbackend.repository.UserRepository;
import com.example.restaurantbackend.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final TokenService tokenService;

    /**
     * Constructor AuthController
     *
     * @param authenticationManager AuthenticationManager
     * @param userRepository UserRepository
     * @param tokenService TokenService
     */
    public UserService(AuthenticationManager authenticationManager, UserRepository userRepository,
                       TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }


    /**
     * Method to get token
     *
     * @param data AuthenticationDTO
     * @return token String
     */
    public String GetToken(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.name(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((User) auth.getPrincipal());

    }

    /**
     * Method to create user
     *
     * @param data RegisterDTO
     * @return String
     */
    public String CreateUser(RegisterDTO data) {

        if (this.userRepository.findByName(data.name()).isPresent()) return "badRequest";

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        var newUser = User.UserBuilder().name(data.name())
                                        .password(encryptedPassword)
                                        .role(data.role());

        this.userRepository.save(newUser);

        return "ok";
    }

}
