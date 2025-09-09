package org.example.restaurantapplication.repository;

import org.example.restaurantapplication.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findByDateCreationAfter(Date date);
    Menu findByNom(String nom);
}
