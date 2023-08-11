package com.example.restaurantbackend.controllers;

import com.example.restaurantbackend.domain.DTO.MenuDTO;
import com.example.restaurantbackend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MenuController {

    MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/menu")
    public ResponseEntity<MenuDTO> getMenu(@RequestBody MenuDTO menuDTO) {


        if (verification(menuDTO) == null) {
            return ResponseEntity.badRequest().body(menuDTO);
        }

        MenuDTO createdMenuDTO = menuService.createMenu(menuDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenuDTO);
    }


    public MenuDTO verification(MenuDTO menuDTO) {
        if (menuDTO.name() == null || menuDTO.name().isEmpty()) {
            return null;
        }
        if (menuDTO.ingredients() == null || menuDTO.ingredients().isEmpty()) {
            return null;
        }
        if (menuDTO.type() == null || menuDTO.type().isEmpty()) {
            return null;
        }
        if (menuDTO.restriction() == null || menuDTO.restriction().isEmpty()) {
            return null;
        }
        if (menuDTO.price() == 0 || menuDTO.price() < 0) {
            return null;
        }
        if (menuDTO.image() == null || menuDTO.image().isEmpty()) {
            return null;
        }
        return menuDTO;
    }
}
