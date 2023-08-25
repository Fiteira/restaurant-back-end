package com.example.restaurantbackend.service;


import com.example.restaurantbackend.domain.DTO.MenuDTO;
import com.example.restaurantbackend.domain.Menu;
import com.example.restaurantbackend.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    public MenuDTO getMenuById(Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElse(null);
        if (menu == null) return null;
        return MenuDTO.fromMenu(menu);
    }


    public List<MenuDTO> getAllMenu() {
        List<Menu> menuList = menuRepository.findAll();

        List<MenuDTO> menuDTOList = new ArrayList<>();
        for (Menu menu : menuList) {
            menuDTOList.add(MenuDTO.fromMenu(menu));
        }

        return menuDTOList;
    }

    public MenuDTO editMenu(Long menuId, MenuDTO menuDTOtoEdit) {
        Menu menu = menuRepository.findById(menuId).orElse(null);

        if (menu == null) return null;

        menu.setName(menuDTOtoEdit.name());
        menu.setIngredients(menuDTOtoEdit.ingredients());
        menu.setType(menuDTOtoEdit.type());
        menu.setRestriction(menuDTOtoEdit.restriction());
        menu.setPrice(menuDTOtoEdit.price());
        menu.setImage(menuDTOtoEdit.image());

        Menu menuSave = menuRepository.save(menu);

        return MenuDTO.fromMenu(menuSave);
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
