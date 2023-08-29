package com.example.restaurantbackend.service;


import com.example.restaurantbackend.domain.DTO.MenuDTO;
import com.example.restaurantbackend.domain.DTO.TableFoodDTO;
import com.example.restaurantbackend.domain.Menu;
import com.example.restaurantbackend.domain.TableFood;
import com.example.restaurantbackend.repository.TableFoodRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableFoodService {

    private final TableFoodRepository tableFoodRepository;

    public TableFoodService(TableFoodRepository tableFoodRepository) {
        this.tableFoodRepository = tableFoodRepository;
    }

    public TableFoodDTO createTableFood(TableFoodDTO tableFoodDTO) {

        TableFood tableFood = TableFood.TableFoodBuilder()
                .id(tableFoodDTO.id())
                .positionX(tableFoodDTO.positionX())
                .positionY(tableFoodDTO.positionY());


        TableFood tableFoodSave = tableFoodRepository.save(tableFood);

        return TableFoodDTO.fromTableFood(tableFoodSave);
    }


    public TableFoodDTO getTableFoodById(Long tableFoodId) {
        TableFood tableFood = tableFoodRepository.findById(tableFoodId).orElse(null);
        if (tableFood == null) return null;
        return TableFoodDTO.fromTableFood(tableFood);
    }


    public List<TableFoodDTO> getAllTableFood() {
        List<TableFood> tableFoodList = tableFoodRepository.findAll();

        List<TableFoodDTO> tableFoodDTOList = new ArrayList<>();
        for (TableFood tableFood : tableFoodList) {
            tableFoodDTOList.add(TableFoodDTO.fromTableFood(tableFood));
        }

        return tableFoodDTOList;
    }

    public TableFoodDTO editTableFood(Long tableFoodId, TableFoodDTO tableFoodDTOtoEdit) {
        TableFood tableFood = tableFoodRepository.findById(tableFoodId).orElse(null);

        if (tableFood == null) return null;

        tableFood.setPositionX(tableFoodDTOtoEdit.positionX());
        tableFood.setPositionY(tableFoodDTOtoEdit.positionY());

        TableFood tableFoodSave = tableFoodRepository.save(tableFood);

        return TableFoodDTO.fromTableFood(tableFoodSave);
    }

}
