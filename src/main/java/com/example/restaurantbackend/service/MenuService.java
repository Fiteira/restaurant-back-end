package com.example.restaurantbackend.service;


import com.example.restaurantbackend.domain.DTO.MenuDTO;
import com.example.restaurantbackend.domain.Menu;
import com.example.restaurantbackend.repository.MenuRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public MenuDTO createMenu(MenuDTO menuDTO) {

        Menu menu = Menu.MenuBuilder()
                .name(menuDTO.name())
                .ingredients(menuDTO.ingredients())
                .type(menuDTO.type())
                .restriction(menuDTO.restriction())
                .price(menuDTO.price())
                .image(menuDTO.image());

        Menu menuSave = menuRepository.save(menu);

        return MenuDTO.fromMenu(menuSave);
    }
}
