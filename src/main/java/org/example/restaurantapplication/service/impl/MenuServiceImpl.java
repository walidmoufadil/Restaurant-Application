package org.example.restaurantapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.restaurantapplication.dto.MenuDTO;
import org.example.restaurantapplication.dto.MenuRequestDTO;
import org.example.restaurantapplication.entity.Menu;
import org.example.restaurantapplication.mapper.MenuMapper;
import org.example.restaurantapplication.repository.MenuRepository;
import org.example.restaurantapplication.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public MenuDTO save(MenuRequestDTO menuRequestDTO) {
        Menu menu = MenuMapper.toEntity(menuRequestDTO);
        menu.setDateCreation(new Date());
        return MenuMapper.toDTO(menuRepository.save(menu));
    }

    @Override
    public MenuDTO update(MenuDTO menuDTO) {
        Menu existingMenu = menuRepository.findById(menuDTO.getId())
                .orElseThrow(() -> new RuntimeException("Menu non trouvé avec l'ID: " + menuDTO.getId()));

        Menu menu = MenuMapper.toEntity(menuDTO);
        menu.setDateCreation(existingMenu.getDateCreation());
        return MenuMapper.toDTO(menuRepository.save(menu));
    }

    @Override
    public MenuDTO findById(int id) {
        return menuRepository.findById(id)
                .map(MenuMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Menu non trouvé avec l'ID: " + id));
    }

    @Override
    public List<MenuDTO> findAll() {
        return menuRepository.findAll().stream()
                .map(MenuMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        menuRepository.deleteById(id);
    }

    @Override
    public MenuDTO findByNom(String nom) {
        return menuRepository.findByNom(nom)
                .map(MenuMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Menu non trouvé avec le nom: " + nom));
    }

    @Override
    public List<MenuDTO> findByDateCreationAfter(Date date) {
        return menuRepository.findByDateCreationAfter(date).stream()
                .map(MenuMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MenuDTO findCurrentMenu() {
        return menuRepository.findAll().stream()
                .max(Comparator.comparing(Menu::getDateCreation))
                .map(MenuMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Aucun menu actif trouvé"));
    }
}
