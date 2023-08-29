package com.example.restaurantbackend.domain.DTO;

import com.example.restaurantbackend.domain.Menu;
import com.example.restaurantbackend.domain.TableFood;

public record TableFoodDTO(Long id, int positionX, int positionY) {

    public static TableFoodDTO fromTableFood(TableFood tableFood) {
        return new TableFoodDTO(
                tableFood.getId(),
                tableFood.getPositionX(),
                tableFood.getPositionY()
                );

    }
}
