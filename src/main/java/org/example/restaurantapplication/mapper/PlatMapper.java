package org.example.restaurantapplication.mapper;

import org.example.restaurantapplication.dto.PlatDTO;
import org.example.restaurantapplication.dto.PlatRequestDTO;
import org.example.restaurantapplication.entity.Plat;
import org.example.restaurantapplication.entity.Menu;

public class PlatMapper {

    public static PlatDTO toDTO(Plat plat) {
        if (plat == null) return null;

        return PlatDTO.builder()
                .id(plat.getId())
                .nom(plat.getNom())
                .description(plat.getDescription())
                .prix(plat.getPrix())
                .disponibilite(plat.isDisponibilite())
                .menuId(plat.getMenu().getId())
                .build();
    }

    public static Plat toEntity(PlatRequestDTO dto, Menu menu) {
        if (dto == null) return null;

        return Plat.builder()
                .nom(dto.getNom())
                .description(dto.getDescription())
                .prix(dto.getPrix())
                .disponibilite(dto.isDisponibilite())
                .menu(menu)
                .build();
    }

    public static Plat toEntity(PlatDTO dto, Menu menu) {
        if (dto == null) return null;

        return Plat.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .description(dto.getDescription())
                .prix(dto.getPrix())
                .disponibilite(dto.isDisponibilite())
                .menu(menu)
                .build();
    }
}
