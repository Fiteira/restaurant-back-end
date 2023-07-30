package com.example.restaurantbackend;

import com.example.restaurantbackend.domain.DTO.userDTO.AuthenticationDTO;
import com.example.restaurantbackend.domain.DTO.userDTO.RegisterDTO;
import com.example.restaurantbackend.domain.DTO.userDTO.UserDTO;
import com.example.restaurantbackend.domain.user.User;
import com.example.restaurantbackend.domain.user.UserRole;

public class DataExample {

    public User userAdmin() {
        return new User()
                        .id("1L")
                        .name("name")
                        .password("password")
                        .role(UserRole.ADMIN);
    }

    public User userChef() {
        return new User()
                        .id("2L")
                        .name("name2")
                        .password("password2")
                        .role(UserRole.CHEF);
    }

    public User userWaiter() {
        return new User()
                        .id("3L")
                        .name("name3")
                        .password("password3")
                        .role(UserRole.WAITER);
    }

    public UserDTO getUserDTO() {
        return new UserDTO("1L", "name", "ADMIN");
    }


    public AuthenticationDTO getAuthenticationDTO() {
        return new AuthenticationDTO("admin", "admin");
    }

    public RegisterDTO getRegisterDTO() {
        return new RegisterDTO("admin", "admin", UserRole.ADMIN);
    }


}
