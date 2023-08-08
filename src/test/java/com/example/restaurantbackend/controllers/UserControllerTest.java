package com.example.restaurantbackend.controllers;

import com.example.restaurantbackend.DataExample;
import com.example.restaurantbackend.domain.DTO.userDTO.RegisterDTO;
import com.example.restaurantbackend.domain.DTO.userDTO.UserDTO;
import com.example.restaurantbackend.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {


    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;


    DataExample dataExample = new DataExample();

    @Test
    public void editUserOk() {

        //Given
        String userUUID = dataExample.getUserDTO().id();
        RegisterDTO userToEdit = dataExample.getRegisterDTO();
        UserDTO userDTO = dataExample.getUserDTO();


        //When
        Mockito.when(userService.editUser(eq(userUUID), eq(userToEdit)))
                .thenReturn(Optional.of(userDTO));

        ResponseEntity<Optional<UserDTO>> response = userController.editUser(userUUID, userToEdit);


        //Then
        verify(userService).editUser(eq(userUUID), eq(userToEdit));
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertTrue(Objects.requireNonNull(response.getBody()).isPresent());

    }

    @Test
    public void editUserBadRequest() {

        // Given
        String userUUID = dataExample.getUserDTO().id();
        RegisterDTO userToEdit = dataExample.getRegisterDTO();

        // When
        Mockito.when(userService.editUser(eq(userUUID), eq(userToEdit)))
                .thenReturn(Optional.empty());

        ResponseEntity<Optional<UserDTO>> response = userController.editUser(userUUID, userToEdit);

        // Then
        verify(userService).editUser(eq(userUUID), eq(userToEdit));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    public void getUserOk() {

        //Given
        String userUUID = dataExample.getUserDTO().id();
        UserDTO userDTO = dataExample.getUserDTO();

        //When

        Mockito.when(userService.getUser(eq(userUUID)))
                .thenReturn(Optional.of(userDTO));

        ResponseEntity<Optional<UserDTO>> response = userController.getUser(userUUID);

        //Then

        verify(userService).getUser(eq(userUUID));
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertTrue(Objects.requireNonNull(response.getBody()).isPresent());
    }

    @Test
    public void getUserBadRequest() {

        //Given
        String userUUID = dataExample.getUserDTO().id();

        //When
        Mockito.when(userService.getUser(eq(userUUID)))
                .thenReturn(Optional.empty());

        ResponseEntity<Optional<UserDTO>> response = userController.getUser(userUUID);

        //Then
        verify(userService).getUser(eq(userUUID));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getAllUsersOk() {

        //Given
        List<UserDTO> userDTOList = dataExample.getUserDTOList();


        //When
        Mockito.when(userService.getAllUsers())
                .thenReturn(dataExample.getUserDTOList());
        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        //Then
        verify(userService, times(2)).getAllUsers();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(userDTOList, response.getBody());
    }

    @Test
    public void getAllUsersBadRequest() {

        //Given
        List<UserDTO> userDTOList = dataExample.getUserDTOList();

        //When
        Mockito.when(userService.getAllUsers())
                .thenReturn(dataExample.getUserDTOList());
        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        //Then
        verify(userService, times(2)).getAllUsers();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(userDTOList, response.getBody());
    }

}
