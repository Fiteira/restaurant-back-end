package com.example.restaurantbackend.domain.DTO.userDTO;

import com.example.restaurantbackend.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public record UserDTO(String id, String name, String role) {

    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getRole());
    }

    public static List<UserDTO> fromUserList(List<User> users) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : users) {
            userDTOList.add(fromUser(user));
        }
        return userDTOList;
    }
}
