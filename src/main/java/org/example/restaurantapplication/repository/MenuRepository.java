package org.example.restaurantapplication.repository;

import org.example.restaurantapplication.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    Optional<Menu> findByNom(String nom);
    List<Menu> findByDateCreationAfter(Date date);
}
