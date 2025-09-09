package org.example.restaurantapplication.repository;

import org.example.restaurantapplication.entity.Plat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatRepository extends JpaRepository<Plat, Integer> {
    List<Plat> findByMenuId(Integer menuId);
    List<Plat> findByDisponibilite(boolean disponibilite);
    Plat findByNom(String nom);
}
