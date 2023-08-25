package com.example.restaurantbackend;

import com.example.restaurantbackend.domain.DTO.MenuDTO;
import com.example.restaurantbackend.domain.DTO.userDTO.AuthenticationDTO;
import com.example.restaurantbackend.domain.DTO.userDTO.RegisterDTO;
import com.example.restaurantbackend.domain.DTO.userDTO.UserDTO;
import com.example.restaurantbackend.domain.Menu;
import com.example.restaurantbackend.domain.user.User;
import com.example.restaurantbackend.domain.user.UserRole;

import java.util.List;

public class DataExample {


    //USER

    private final List<UserDTO> userDTOList = List.of(
                    new UserDTO("1L", "name", "ADMIN"),
                    new UserDTO("2L", "name2", "CHEF"),
                    new UserDTO("3L", "name3", "WAITER")
    );

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


    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }


    //MENU

    public MenuDTO getMenuDTO() {
        return new MenuDTO(1L, "menu1", "description1", "type1", "restriction1", 1.0f, "image1");
    }

    public MenuDTO getMenuDTOInvalid() {
        return new MenuDTO(1L, "menu1", "description1", "type1", "restriction1", -1.0f, "image1");
    }

    public Menu menu1() {
        return new Menu()
                .id(1L)
                .name("menu1")
                .ingredients("description1")
                .type("type1")
                .restriction("restriction1")
                .price(1.0f)
                .image("image1");
    }

    public Menu menu2() {
        return new Menu()
                .id(2L)
                .name("menu2")
                .ingredients("description2")
                .type("type2")
                .restriction("restriction2")
                .price(2.0f)
                .image("image2");
    }

    private final List<MenuDTO> menuDTOList = List.of(
            new MenuDTO(1L, "menu1", "description1", "type1", "restriction1", 1.0f, "image1"),
            new MenuDTO(2L, "menu2", "description2", "type2", "restriction2", 2.0f, "image2"),
            new MenuDTO(3L, "menu3", "description3", "type3", "restriction3", 3.0f, "image3")
    );

    public List<MenuDTO> getMenuDTOList() {
        return menuDTOList;
    }

}
