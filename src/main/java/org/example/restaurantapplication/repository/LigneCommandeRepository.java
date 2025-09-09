package org.example.restaurantapplication.repository;

import org.example.restaurantapplication.entity.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Integer> {
    List<LigneCommande> findByCommandeId(Integer commandeId);
    List<LigneCommande> findByPlatId(Integer platId);
}
