package com.example.restaurantbackend.service;

import com.example.restaurantbackend.domain.DTO.userDTO.AuthenticationDTO;
import com.example.restaurantbackend.domain.DTO.userDTO.RegisterDTO;
import com.example.restaurantbackend.domain.DTO.userDTO.UserDTO;
import com.example.restaurantbackend.domain.user.User;
import com.example.restaurantbackend.repository.UserRepository;
import com.example.restaurantbackend.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
     * @return token String the authentication
     */
    public String getToken(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.name(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((User) auth.getPrincipal());

    }

    /**
     * Method to create user
     *
     * @param data RegisterDTO
     * @return String "ok" or "badRequest"
     */
    public String createUser(RegisterDTO data) {

        if (this.userRepository.findByName(data.name()).isPresent()) return "badRequest";

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        var newUser = User.UserBuilder().name(data.name())
                                        .password(encryptedPassword)
                                        .role(data.role());

        this.userRepository.save(newUser);

        return "ok";
    }


    /**
     * Method to get user
     *
     * @param id String
     * @return String User
     */
    public Optional<UserDTO> getUser(String id) {
        Optional<User> user = this.userRepository.findById(id);

        return user.map(value -> new UserDTO(value.getId(),
                value.getName(),
                value.getRole()));

    }

    /**
     * Method to get all users
     *
     * @return List<UserDTO> Users DTO
     */
    public List<UserDTO> getAllUsers() {

       return (UserDTO.fromUserList(this.userRepository.findAll()));

    }

    /**
     * Method to edit user
     *
     * @param id String
     * @param data RegisterDTO
     * @return UserDTO User
     */
    public Optional<UserDTO> editUser(final String id, final RegisterDTO data) {
        Optional<User> user = this.userRepository.findById(id);

        if(user.isEmpty()) return Optional.empty();


        user.get().setName(data.name());
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        user.get().setPassword(encryptedPassword);
        user.get().setRole(data.role());
        this.userRepository.save(user.get());

        return Optional.of(new UserDTO(user.get().getId(), user.get().getName(), user.get().getRole()));
    }

}
