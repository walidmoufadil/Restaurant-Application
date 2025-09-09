package org.example.restaurantapplication.service;

import org.example.restaurantapplication.dto.MenuDTO;
import org.example.restaurantapplication.dto.MenuRequestDTO;
import java.util.List;
import java.util.Date;

public interface MenuService {
    MenuDTO save(MenuRequestDTO menuRequestDTO);
    MenuDTO update(MenuDTO menuDTO);
    MenuDTO findById(int id);
    List<MenuDTO> findAll();
    void deleteById(int id);
    MenuDTO findByNom(String nom);
    List<MenuDTO> findByDateCreationAfter(Date date);
}
