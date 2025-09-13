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
import org.springframework.ai.tool.annotation.Tool;
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
//    @Tool(
//            name = "createPlat",
//            description = "Create a new plat (dish) in the system. Takes PlatRequestDTO as input containing plat details (name, description, price, availability, menuId) without ID. Returns PlatDTO with generated ID. Throws exception if specified menu not found."
//    )
    public PlatDTO save(PlatRequestDTO platRequestDTO) {
        Menu menu = menuRepository.findById(platRequestDTO.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu non trouvé"));
        Plat plat = PlatMapper.toEntity(platRequestDTO, menu);
        Plat savedPlat = platRepository.save(plat);
        return PlatMapper.toDTO(savedPlat);
    }

    @Override
//    @Tool(
//            name = "updatePlat",
//            description = "Update an existing plat's information. Takes PlatDTO as input containing updated plat details including ID. Returns updated PlatDTO. Throws exception if plat or menu not found."
//    )
    public PlatDTO update(PlatDTO platDTO) {
        Menu menu = menuRepository.findById(platDTO.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu non trouvé"));
        Plat plat = PlatMapper.toEntity(platDTO, menu);
        Plat updatedPlat = platRepository.save(plat);
        return PlatMapper.toDTO(updatedPlat);
    }

    @Override
//    @Tool(
//            name = "findPlatById",
//            description = "Find a plat by its ID. Takes plat ID as input parameter. Returns PlatDTO if found, throws exception if plat not found with the specified ID."
//    )
    public PlatDTO findById(int id) {
        return platRepository.findById(id)
                .map(PlatMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Plat non trouvé avec l'id: " + id));
    }

    @Override
    @Tool(
            name = "findAllPlats",
            description = "Retrieve a list of all plats in the system. Takes no input parameters. Returns List<PlatDTO> containing all plats' information including their names, descriptions, prices and availability status."
    )
    public List<PlatDTO> findAll() {
        return platRepository.findAll().stream()
                .map(PlatMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
//    @Tool(
//            name = "deletePlatById",
//            description = "Delete a plat from the system by its ID. Takes plat ID as input parameter. Returns void. Throws exception if plat not found. This will also remove the plat from any associated order lines."
//    )
    public void deleteById(int id) {
        platRepository.deleteById(id);
    }

    @Override
    @Tool(
            name = "findPlatsByMenuId",
            description = "Find all plats belonging to a specific menu. Takes menu ID as input parameter. Returns List<PlatDTO> containing all plats associated with the specified menu."
    )
    public List<PlatDTO> findByMenuId(Integer menuId) {
        return platRepository.findByMenuId(menuId).stream()
                .map(PlatMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Tool(
            name = "findPlatsByAvailability",
            description = "Find all plats with a specific availability status. Takes boolean availability parameter. Returns List<PlatDTO> containing all plats that match the specified availability (true for available, false for unavailable)."
    )
    public List<PlatDTO> findByDisponibilite(boolean disponibilite) {
        return platRepository.findByDisponibilite(disponibilite).stream()
                .map(PlatMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Tool(
            name = "findPlatByName",
            description = "Find a plat by its name. Takes plat name as input parameter. Returns PlatDTO if found, null if no plat exists with the specified name."
    )
    public PlatDTO findByNom(String nom) {
        Plat plat = platRepository.findByNom(nom);
        return PlatMapper.toDTO(plat);
    }
}
