package org.example.restaurantapplication.mapper;

import org.example.restaurantapplication.dto.MenuDTO;
import org.example.restaurantapplication.dto.MenuRequestDTO;
import org.example.restaurantapplication.entity.Menu;

public class MenuMapper {

    public static MenuDTO toDTO(Menu menu) {
        if (menu == null) return null;

        return MenuDTO.builder()
                .id(menu.getId())
                .nom(menu.getNom())
                .dateCreation(menu.getDateCreation())
                .build();
    }

    public static Menu toEntity(MenuRequestDTO dto) {
        if (dto == null) return null;

        return Menu.builder()
                .nom(dto.getNom())
                .dateCreation(dto.getDateCreation())
                .build();
    }

    public static Menu toEntity(MenuDTO dto) {
        if (dto == null) return null;

        return Menu.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .dateCreation(dto.getDateCreation())
                .build();
    }
}
