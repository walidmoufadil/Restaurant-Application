package org.example.restaurantapplication.repository;

import org.example.restaurantapplication.entity.Commande;
import org.example.restaurantapplication.entity.StatutCommande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    List<Commande> findByClientId(Integer clientId);
    List<Commande> findByStatut(StatutCommande statut);
    List<Commande> findByStatutNotIn(List<StatutCommande> statuts);
}
