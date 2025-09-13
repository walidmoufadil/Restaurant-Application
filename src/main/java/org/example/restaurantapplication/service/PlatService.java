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
    public List<PlatDTO> findByDisponibilite(boolean disponibilite);
    public PlatDTO findByNom(String nom);
    public List<PlatDTO> findByMenuId(Integer menuId);
}
