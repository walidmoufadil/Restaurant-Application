package org.example.restaurantapplication.service;

import org.example.restaurantapplication.dto.PlatDTO;
import org.example.restaurantapplication.dto.PlatRequestDTO;
import java.util.List;

public interface PlatService {
    PlatDTO save(PlatRequestDTO platRequestDTO);
    PlatDTO update(PlatDTO platDTO);
    PlatDTO findById(int id);
    List<PlatDTO> findAll();
    void deleteById(int id);
    List<PlatDTO> findByMenuId(Integer menuId);
    List<PlatDTO> findByDisponibilite(boolean disponibilite);
    PlatDTO findByNom(String nom);
}
