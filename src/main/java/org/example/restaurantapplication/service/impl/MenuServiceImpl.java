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
        Menu savedMenu = menuRepository.save(menu);
        return MenuMapper.toDTO(savedMenu);
    }

    @Override
    public MenuDTO update(MenuDTO menuDTO) {
        Menu menu = MenuMapper.toEntity(menuDTO);
        Menu updatedMenu = menuRepository.save(menu);
        return MenuMapper.toDTO(updatedMenu);
    }

    @Override
    public MenuDTO findById(int id) {
        return menuRepository.findById(id)
                .map(MenuMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Menu non trouvé avec l'id: " + id));
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
        Menu menu = menuRepository.findByNom(nom);
        return MenuMapper.toDTO(menu);
    }

    @Override
    public List<MenuDTO> findByDateCreationAfter(Date date) {
        return menuRepository.findByDateCreationAfter(date).stream()
                .map(MenuMapper::toDTO)
                .collect(Collectors.toList());
    }
}
