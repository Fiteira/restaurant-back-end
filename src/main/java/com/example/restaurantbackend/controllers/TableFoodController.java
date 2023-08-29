package com.example.restaurantbackend.controllers;


import com.example.restaurantbackend.domain.DTO.TableFoodDTO;
import com.example.restaurantbackend.service.TableFoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TableFoodController {

    TableFoodService tableFoodService;

    public TableFoodController(TableFoodService tableFoodService) {
        this.tableFoodService = tableFoodService;
    }

    @PostMapping("/tableFood")
    public ResponseEntity<TableFoodDTO> createTableFood(@RequestBody TableFoodDTO TableFoodDTO) {

        if(TableFoodDTO == null){
            return ResponseEntity.badRequest().body(null);
        }

        TableFoodDTO createdTableFoodDTO = tableFoodService.createTableFood(TableFoodDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTableFoodDTO);
    }

    @GetMapping("/tableFood/{tableFoodId}")
    public ResponseEntity<TableFoodDTO> getTableFoodById(@PathVariable final Long tableFoodId) {

        TableFoodDTO tableFoodDTO = tableFoodService.getTableFoodById(tableFoodId);

        if (tableFoodDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(tableFoodDTO);
    }

    @GetMapping("/tableFood")
    public ResponseEntity<List<TableFoodDTO>> getAllTableFood() {

        List<TableFoodDTO> tableFoodDTOList = tableFoodService.getAllTableFood();

        if (tableFoodDTOList == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(tableFoodDTOList);
    }

    @PutMapping("/tableFood/{tableFoodId}")
    public ResponseEntity<TableFoodDTO> editTableFood(@PathVariable final Long tableFoodId, @RequestBody TableFoodDTO tableFoodDTOtoEdit) {

        TableFoodDTO tableFoodDTO = tableFoodService.editTableFood(tableFoodId, tableFoodDTOtoEdit);

        if (tableFoodDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(tableFoodDTO);
    }

}
