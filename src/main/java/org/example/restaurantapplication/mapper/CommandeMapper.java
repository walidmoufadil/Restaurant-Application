package org.example.restaurantapplication.mapper;

import org.example.restaurantapplication.dto.CommandeDTO;
import org.example.restaurantapplication.dto.CommandeRequestDTO;
import org.example.restaurantapplication.entity.Commande;
import org.example.restaurantapplication.entity.LigneCommande;
import org.example.restaurantapplication.repository.PlatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommandeMapper {

    private final PlatRepository platRepository;

    public CommandeDTO toDTO(Commande commande) {
        if (commande == null) return null;

        return CommandeDTO.builder()
                .id(commande.getId())
                .dateCommande(commande.getDateCommande())
                .statut(commande.getStatut())
                .clientId(commande.getClient().getId())
                .telephone(commande.getClient().getTelephone())
                .lignesCommande(commande.getLignesCommande().stream()
                        .map(LigneCommandeMapper::toDTO)
                        .toList())
                .build();
    }

    public Commande toEntity(CommandeRequestDTO dto) {
        if (dto == null) return null;

        Commande commande = Commande.builder()
                .dateCommande(dto.getDateCommande())
                .statut(dto.getStatut())
                .build();

        if (dto.getLignesCommande() != null) {
            commande.setLignesCommande(
                dto.getLignesCommande().stream()
                    .map(ligneDTO -> {
                        var plat = platRepository.findById(ligneDTO.getPlatId())
                            .orElseThrow(() -> new RuntimeException("Plat non trouvé avec l'ID: " + ligneDTO.getPlatId()));
                        return LigneCommandeMapper.toEntity(ligneDTO, commande, plat);
                    })
                    .collect(Collectors.toList())
            );
        } else {
            commande.setLignesCommande(new ArrayList<>());
        }

        return commande;
    }

    public Commande toEntity(CommandeDTO dto) {
        if (dto == null) return null;

        Commande commande = Commande.builder()
                .id(dto.getId())
                .dateCommande(dto.getDateCommande())
                .statut(dto.getStatut())
                .build();

        if (dto.getLignesCommande() != null) {
            commande.setLignesCommande(
                dto.getLignesCommande().stream()
                    .map(ligneDTO -> {
                        var plat = platRepository.findById(ligneDTO.getPlatId())
                            .orElseThrow(() -> new RuntimeException("Plat non trouvé avec l'ID: " + ligneDTO.getPlatId()));
                        return LigneCommandeMapper.toEntity(ligneDTO, commande, plat);
                    })
                    .collect(Collectors.toList())
            );
        } else {
            commande.setLignesCommande(new ArrayList<>());
        }

        return commande;
    }
}
