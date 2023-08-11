package com.example.restaurantbackend.domain.DTO;


import com.example.restaurantbackend.domain.Menu;

import java.util.ArrayList;
import java.util.List;

public record MenuDTO(
        Long id,
        String name,
        String ingredients,
        String type,
        String restriction,
        float price,
        String image) {

    public static MenuDTO fromMenu(Menu menu) {
        return new MenuDTO(
                menu.getId(),
                menu.getName(),
                menu.getIngredients(),
                menu.getType(),
                menu.getRestriction(),
                menu.getPrice(),
                menu.getImage());
    }

    public static List<MenuDTO> fromMenuList(List<Menu> menus) {
        List<MenuDTO> menuDTOList = new ArrayList<>();
        for (Menu menu : menus) {
            menuDTOList.add(fromMenu(menu));
        }
        return menuDTOList;
    }
}
