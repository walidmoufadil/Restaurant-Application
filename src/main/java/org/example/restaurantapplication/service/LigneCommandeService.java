package org.example.restaurantapplication.service;

import org.example.restaurantapplication.dto.LigneCommandeDTO;
import org.example.restaurantapplication.dto.LigneCommandeRequestDTO;
import java.util.List;

public interface LigneCommandeService {
    LigneCommandeDTO save(LigneCommandeRequestDTO ligneCommandeRequestDTO);
    LigneCommandeDTO update(LigneCommandeDTO ligneCommandeDTO);
    LigneCommandeDTO findById(int id);
    List<LigneCommandeDTO> findAll();
    void deleteById(int id);
    List<LigneCommandeDTO> findByCommandeId(Integer commandeId);
    List<LigneCommandeDTO> findByPlatId(Integer platId);
}
