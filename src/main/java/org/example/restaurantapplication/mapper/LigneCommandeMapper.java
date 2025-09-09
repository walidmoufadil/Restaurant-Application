package org.example.restaurantapplication.mapper;

import org.example.restaurantapplication.dto.LigneCommandeDTO;
import org.example.restaurantapplication.dto.LigneCommandeRequestDTO;
import org.example.restaurantapplication.entity.LigneCommande;
import org.example.restaurantapplication.entity.Commande;
import org.example.restaurantapplication.entity.Plat;

public class LigneCommandeMapper {

    public static LigneCommandeDTO toDTO(LigneCommande ligneCommande) {
        if (ligneCommande == null) return null;

        return LigneCommandeDTO.builder()
                .id(ligneCommande.getId())
                .quantite(ligneCommande.getQuantite())
                .commandeId(ligneCommande.getCommande().getId())
                .platId(ligneCommande.getPlat().getId())
                .build();
    }

    public static LigneCommande toEntity(LigneCommandeRequestDTO dto, Commande commande, Plat plat) {
        if (dto == null) return null;

        return LigneCommande.builder()
                .quantite(dto.getQuantite())
                .commande(commande)
                .plat(plat)
                .build();
    }

    public static LigneCommande toEntity(LigneCommandeDTO dto, Commande commande, Plat plat) {
        if (dto == null) return null;

        return LigneCommande.builder()
                .id(dto.getId())
                .quantite(dto.getQuantite())
                .commande(commande)
                .plat(plat)
                .build();
    }
}
