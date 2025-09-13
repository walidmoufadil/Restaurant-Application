package org.example.restaurantapplication.service;

import org.example.restaurantapplication.dto.CommandeDTO;
import org.example.restaurantapplication.dto.CommandeRequestDTO;
import org.example.restaurantapplication.entity.StatutCommande;
import java.util.List;

public interface CommandeService {
    CommandeDTO save(CommandeRequestDTO commandeRequestDTO);
    CommandeDTO update(CommandeDTO commandeDTO);
    CommandeDTO updateStatus(int commandeId, StatutCommande statut);
    CommandeDTO findById(int id);
    List<CommandeDTO> findAll();
    void deleteById(int id);
    List<CommandeDTO> findByClientId(Integer clientId);
    List<CommandeDTO> findByStatut(StatutCommande statut);
    List<CommandeDTO> findByClientTelephone(String telephone);
    List<CommandeDTO> findCurrentCommandes();
}
