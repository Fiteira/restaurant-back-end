package com.example.restaurantbackend.service;

import com.example.restaurantbackend.DataExample;
import com.example.restaurantbackend.domain.DTO.userDTO.AuthenticationDTO;
import com.example.restaurantbackend.domain.DTO.userDTO.RegisterDTO;
import com.example.restaurantbackend.domain.DTO.userDTO.UserDTO;
import com.example.restaurantbackend.domain.user.User;
import com.example.restaurantbackend.repository.UserRepository;
import com.example.restaurantbackend.security.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenService tokenService;

    DataExample dataExample = new DataExample();

    @Test
    public void getToken() {

        //Given
        AuthenticationDTO data = dataExample.getAuthenticationDTO();
        User userDetails = dataExample.userAdmin();


        //When
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
        Mockito.when(authenticationManager.authenticate(any())).thenReturn(authentication);

        String expectedToken = "testToken";
        Mockito.when(tokenService.generateToken(any())).thenReturn(expectedToken);
        String actualToken = userService.getToken(data);

        //Then
        Assertions.assertEquals(expectedToken, actualToken);
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenService).generateToken(userDetails);

    }


    @Test
    public void createUserOk() {

        //Given
        RegisterDTO userRegisterDTO = dataExample.getRegisterDTO();
        Mockito.when(userRepository.findByName(userRegisterDTO.name())).thenReturn(Optional.empty());

        //When
        String result = userService.createUser(userRegisterDTO);

        //Then
        Assertions.assertEquals("ok", result);
        verify(userRepository).findByName(userRegisterDTO.name());
        verify(userRepository).save(any(User.class));

    }

    @Test
    public void createUserBadRequest() {

        //Given
        RegisterDTO userRegisterDTO = dataExample.getRegisterDTO();

        //create mock of user exist
        Mockito.when(userRepository.findByName(userRegisterDTO.name())).thenReturn(Optional.of(new User()));

        //When
        String result = userService.createUser(userRegisterDTO);

        //Then
        Assertions.assertEquals("badRequest", result);
        verify(userRepository).findByName(userRegisterDTO.name());
        verify(userRepository, never()).save(any(User.class));

    }


    @Test
    public void getUserSuccess() {

        //Given
        User user = dataExample.userAdmin();
        String id = user.getId();

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        //When
        Optional<UserDTO> result = userService.getUser(id);

        //Then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(id, result.get().id());
        Assertions.assertEquals("name", result.get().name());
        Assertions.assertEquals("admin", result.get().role());

    }

    @Test
    public void getUserNotFound() {

        //Given
        User user = dataExample.userAdmin();
        String id = user.getId();

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        //When
        Optional<UserDTO> result = userService.getUser(id);

        //Then
        Assertions.assertFalse(result.isPresent());

    }

    @Test
    public void getAllUsersSuccess(){

        //Given
        User userAdmin = dataExample.userAdmin();
        User userChef = dataExample.userChef();
        User userWaiter = dataExample.userWaiter();

        List<User> userList = Arrays.asList(userAdmin, userChef, userWaiter);

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        //When

        List<UserDTO> result = userService.getAllUsers();


        // Then
        Assertions.assertEquals(3, result.size());

        Assertions.assertEquals("1L", result.get(0).id());
        Assertions.assertEquals("name", result.get(0).name());
        Assertions.assertEquals("admin", result.get(0).role());

        Assertions.assertEquals("2L", result.get(1).id());
        Assertions.assertEquals("name2", result.get(1).name());
        Assertions.assertEquals("chef", result.get(1).role());

        Assertions.assertEquals("3L", result.get(2).id());
        Assertions.assertEquals("name3", result.get(2).name());
        Assertions.assertEquals("waiter", result.get(2).role());

    }

    @Test
    public void getAllUsersEmpty(){

        //Given
        List<User> userList = List.of();

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        //When
        List<UserDTO> result = userService.getAllUsers();

        // Then
        Assertions.assertEquals(0, result.size());

    }

    @Test
    public void editUser(){

        // Given
        User user = dataExample.userChef();
        String id = "2L";
        RegisterDTO userGive = dataExample.getRegisterDTO();


        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        //When
        Optional<UserDTO> result = userService.editUser(id, userGive);

        //Then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(id, result.get().id());
        Assertions.assertEquals("admin", result.get().name());
        Assertions.assertEquals("admin", result.get().role());

        verify(userRepository).save(user);
    }

    @Test
    public void editUserNotFound() {

        // Given
        String id = "2L";
        RegisterDTO userGive = dataExample.getRegisterDTO();

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Optional<UserDTO> result = userService.editUser(id, userGive);

        // Then
        Assertions.assertTrue(result.isEmpty());


        verify(userRepository, never()).save(Mockito.any(User.class));
    }

}
