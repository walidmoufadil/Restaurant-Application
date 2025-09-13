package org.example.restaurantapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.restaurantapplication.dto.LigneCommandeDTO;
import org.example.restaurantapplication.dto.LigneCommandeRequestDTO;
import org.example.restaurantapplication.entity.LigneCommande;
import org.example.restaurantapplication.entity.Commande;
import org.example.restaurantapplication.entity.Plat;
import org.example.restaurantapplication.mapper.LigneCommandeMapper;
import org.example.restaurantapplication.repository.LigneCommandeRepository;
import org.example.restaurantapplication.repository.CommandeRepository;
import org.example.restaurantapplication.repository.PlatRepository;
import org.example.restaurantapplication.service.LigneCommandeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
public class LigneCommandeServiceImpl implements LigneCommandeService {

    private final LigneCommandeRepository ligneCommandeRepository;
    private final CommandeRepository commandeRepository;
    private final PlatRepository platRepository;

    @Override
    public LigneCommandeDTO save(LigneCommandeRequestDTO ligneCommandeRequestDTO) {
        Commande commande = commandeRepository.findById(ligneCommandeRequestDTO.getCommandeId())
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        Plat plat = platRepository.findById(ligneCommandeRequestDTO.getPlatId())
                .orElseThrow(() -> new RuntimeException("Plat non trouvé"));

        LigneCommande ligneCommande = LigneCommandeMapper.toEntity(ligneCommandeRequestDTO, commande, plat);
        LigneCommande savedLigneCommande = ligneCommandeRepository.save(ligneCommande);
        return LigneCommandeMapper.toDTO(savedLigneCommande);
    }

    @Override
    public LigneCommandeDTO update(LigneCommandeDTO ligneCommandeDTO) {
        Commande commande = commandeRepository.findById(ligneCommandeDTO.getCommandeId())
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        Plat plat = platRepository.findById(ligneCommandeDTO.getPlatId())
                .orElseThrow(() -> new RuntimeException("Plat non trouvé"));

        LigneCommande ligneCommande = LigneCommandeMapper.toEntity(ligneCommandeDTO, commande, plat);
        LigneCommande updatedLigneCommande = ligneCommandeRepository.save(ligneCommande);
        return LigneCommandeMapper.toDTO(updatedLigneCommande);
    }

    @Override
    public LigneCommandeDTO findById(int id) {
        return ligneCommandeRepository.findById(id)
                .map(LigneCommandeMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("LigneCommande non trouvée avec l'id: " + id));
    }

    @Override
    public List<LigneCommandeDTO> findAll() {
        return ligneCommandeRepository.findAll().stream()
                .map(LigneCommandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        ligneCommandeRepository.deleteById(id);
    }

    @Override
    public List<LigneCommandeDTO> findByCommandeId(Integer commandeId) {
        return ligneCommandeRepository.findByCommandeId(commandeId).stream()
                .map(LigneCommandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeDTO> findByPlatId(Integer platId) {
        return ligneCommandeRepository.findByPlatId(platId).stream()
                .map(LigneCommandeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
