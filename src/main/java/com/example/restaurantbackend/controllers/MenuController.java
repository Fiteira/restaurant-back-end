package com.example.restaurantbackend.controllers;

import com.example.restaurantbackend.domain.DTO.MenuDTO;
import com.example.restaurantbackend.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MenuController {

    MenuService menuService;


    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/menu")
    public ResponseEntity<MenuDTO> createMenu(@RequestBody MenuDTO menuDTO) {


        if (menuService.verification(menuDTO) == null) {
            return ResponseEntity.badRequest().body(menuDTO);
        }

        MenuDTO createdMenuDTO = menuService.createMenu(menuDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenuDTO);
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<MenuDTO> getMenuById(@PathVariable final Long menuId) {

        MenuDTO menuDTO = menuService.getMenuById(menuId);

        if (menuDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(menuDTO);
    }

    @GetMapping("/menu")
    public ResponseEntity<List<MenuDTO>> getAllMenu() {

        List<MenuDTO> menuDTOList = menuService.getAllMenu();

        if (menuDTOList == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(menuDTOList);
    }

    @PutMapping("/menu/{menuId}")
    public ResponseEntity<MenuDTO> editMenu(@PathVariable final Long menuId, @RequestBody MenuDTO menuDTOtoEdit) {

        MenuDTO menuDTO = menuService.editMenu(menuId, menuDTOtoEdit);

        if (menuDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(menuDTO);
    }

}
