package com.example.restaurantbackend.controllers;

import com.example.restaurantbackend.domain.DTO.userDTO.RegisterDTO;
import com.example.restaurantbackend.domain.DTO.userDTO.UserDTO;
import com.example.restaurantbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {


    private final UserService userService;

    /**
     * Constructor AuthController
     *
     * @param userService UserService
     */

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method post to Editing user by UUID
     *
     * @param userUUID String (pathVariable), userToEdit RegisterDTO (request body)
     * @return ResponseEntity
     */
    @PutMapping("/user/{userUUID}")
    public ResponseEntity<Optional <UserDTO>> editUser(@PathVariable final String userUUID,
                                          @RequestBody final RegisterDTO userToEdit ) {

        Optional <UserDTO> userDTO= userService.editUser(userUUID, userToEdit);

        if(userDTO.isEmpty()) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(userDTO);
    }

    /**
     * Method to Get User by UUID
     *
     * @param userUUID String (pathVariable), userToEdit RegisterDTO (request body)
     * @return ResponseEntity
     */
    @GetMapping("/user/{userUUID}")
    public ResponseEntity<Optional<UserDTO>> getUser(@PathVariable final String userUUID) {

        Optional <UserDTO> userDTO = userService.getUser(userUUID);

        if(userDTO.isEmpty()) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        if(userService.getAllUsers().isEmpty()) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(userService.getAllUsers());

    }
}
