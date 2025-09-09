package org.example.restaurantapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.restaurantapplication.dto.PlatDTO;
import org.example.restaurantapplication.dto.PlatRequestDTO;
import org.example.restaurantapplication.entity.Plat;
import org.example.restaurantapplication.entity.Menu;
import org.example.restaurantapplication.mapper.PlatMapper;
import org.example.restaurantapplication.repository.PlatRepository;
import org.example.restaurantapplication.repository.MenuRepository;
import org.example.restaurantapplication.service.PlatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlatServiceImpl implements PlatService {

    private final PlatRepository platRepository;
    private final MenuRepository menuRepository;

    @Override
    public PlatDTO save(PlatRequestDTO platRequestDTO) {
        Menu menu = menuRepository.findById(platRequestDTO.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu non trouvé"));
        Plat plat = PlatMapper.toEntity(platRequestDTO, menu);
        Plat savedPlat = platRepository.save(plat);
        return PlatMapper.toDTO(savedPlat);
    }

    @Override
    public PlatDTO update(PlatDTO platDTO) {
        Menu menu = menuRepository.findById(platDTO.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu non trouvé"));
        Plat plat = PlatMapper.toEntity(platDTO, menu);
        Plat updatedPlat = platRepository.save(plat);
        return PlatMapper.toDTO(updatedPlat);
    }

    @Override
    public PlatDTO findById(int id) {
        return platRepository.findById(id)
                .map(PlatMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Plat non trouvé avec l'id: " + id));
    }

    @Override
    public List<PlatDTO> findAll() {
        return platRepository.findAll().stream()
                .map(PlatMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        platRepository.deleteById(id);
    }

    @Override
    public List<PlatDTO> findByMenuId(Integer menuId) {
        return platRepository.findByMenuId(menuId).stream()
                .map(PlatMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlatDTO> findByDisponibilite(boolean disponibilite) {
        return platRepository.findByDisponibilite(disponibilite).stream()
                .map(PlatMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlatDTO findByNom(String nom) {
        Plat plat = platRepository.findByNom(nom);
        return PlatMapper.toDTO(plat);
    }
}
