package com.example.restaurantbackend.controllers;

import com.example.restaurantbackend.DataExample;
import com.example.restaurantbackend.domain.DTO.MenuDTO;
import com.example.restaurantbackend.service.MenuService;
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


@ExtendWith(MockitoExtension.class)
public class MenuControllerTest {

    DataExample dataExample = new DataExample();

    @InjectMocks
    private MenuController menuController;

    @Mock
    private MenuService menuService;


    @Test
    public void creatMenuOk() {

        //Given
        MenuDTO menuDTO = dataExample.getMenuDTO();

        //When
        Mockito.when(menuService.verification(Mockito.any(MenuDTO.class)))
                .thenReturn(menuDTO);
        Mockito.when(menuService.createMenu(menuDTO))
                .thenReturn(menuDTO);
        ResponseEntity<MenuDTO> response = menuController.createMenu(menuDTO);

        //Then
        Mockito.verify(menuService).createMenu(menuDTO);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(menuDTO, response.getBody());
    }

    @Test
    public void creatMenuBadRequest() {

        //Given
        MenuDTO menuDTOInvalid = dataExample.getMenuDTOInvalid();

        //When
        Mockito.when(menuService.verification(menuDTOInvalid))
                .thenReturn(null);


        ResponseEntity<MenuDTO> response = menuController.createMenu(menuDTOInvalid);

        //Then
        Mockito.verify(menuService).verification(menuDTOInvalid);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals(menuDTOInvalid,response.getBody());

    }

    @Test
    public void getMenuByIdOk() {

        //Given
        Long menuId = dataExample.getMenuDTO().id();
        MenuDTO menuDTO = dataExample.getMenuDTO();

        //When
        Mockito.when(menuService.getMenuById(menuId))
                .thenReturn(menuDTO);

        ResponseEntity<MenuDTO> response = menuController.getMenuById(menuId);

        //Then
        Mockito.verify(menuService).getMenuById(menuId);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(menuDTO, response.getBody());
    }

    @Test
    public void getMenuByIdBadRequest() {

        //Given
        Long menuId = dataExample.getMenuDTO().id();
        MenuDTO menuDTO = dataExample.getMenuDTO();

        //When
        Mockito.when(menuService.getMenuById(menuId))
                .thenReturn(null);

        ResponseEntity<MenuDTO> response = menuController.getMenuById(menuId);

        //Then
        Mockito.verify(menuService).getMenuById(menuId);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void getAllMenuOk() {

        //Given
        List <MenuDTO> listMenuDTO = dataExample.getMenuDTOList();


        //When
        Mockito.when(menuService.getAllMenu())
                .thenReturn(listMenuDTO);

        ResponseEntity<List<MenuDTO>> response = menuController.getAllMenu();

        //Then
        Mockito.verify(menuService).getAllMenu();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(listMenuDTO, response.getBody());
    }

    @Test
    public void getAllMenuBadRequest() {

        //Given
        List<MenuDTO> listMenuDTO = dataExample.getMenuDTOList();

        //When
        Mockito.when(menuService.getAllMenu())
                .thenReturn(null);

        ResponseEntity<List<MenuDTO>> response = menuController.getAllMenu();

        //Then
        Mockito.verify(menuService).getAllMenu();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void editMenuOk() {

        //Given
        Long menuId = dataExample.getMenuDTO().id();
        MenuDTO menuDTOtoEdit = dataExample.getMenuDTO();
        MenuDTO menuDTO = dataExample.getMenuDTO();


        //When
        Mockito.when(menuService.editMenu(menuId, menuDTOtoEdit))
                .thenReturn(menuDTO);

        ResponseEntity<MenuDTO> response = menuController.editMenu(menuId, menuDTOtoEdit);


        //Then
        Mockito.verify(menuService).editMenu(menuId, menuDTOtoEdit);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(menuDTO, response.getBody());
    }

    @Test
    public void editMenuBadRequest() {

        //Given
        Long menuId = dataExample.getMenuDTO().id();
        MenuDTO menuDTOtoEdit = dataExample.getMenuDTO();
        MenuDTO menuDTO = dataExample.getMenuDTO();

        //When
        Mockito.when(menuService.editMenu(menuId, menuDTOtoEdit))
                .thenReturn(null);

        ResponseEntity<MenuDTO> response = menuController.editMenu(menuId, menuDTOtoEdit);

        //Then
        Mockito.verify(menuService).editMenu(menuId, menuDTOtoEdit);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

}
