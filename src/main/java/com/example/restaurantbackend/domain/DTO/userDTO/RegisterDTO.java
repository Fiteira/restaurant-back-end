package com.example.restaurantbackend.domain.DTO.userDTO;

import com.example.restaurantbackend.domain.user.UserRole;

public record RegisterDTO(String name, String password, UserRole role) {

}
