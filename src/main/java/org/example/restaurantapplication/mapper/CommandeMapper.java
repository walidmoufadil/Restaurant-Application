package org.example.restaurantapplication.mapper;

import org.example.restaurantapplication.dto.CommandeDTO;
import org.example.restaurantapplication.dto.CommandeRequestDTO;
import org.example.restaurantapplication.entity.Commande;
import org.example.restaurantapplication.entity.Client;

public class CommandeMapper {

    public static CommandeDTO toDTO(Commande commande) {
        if (commande == null) return null;

        return CommandeDTO.builder()
                .id(commande.getId())
                .dateCommande(commande.getDateCommande())
                .statut(commande.getStatut())
                .clientId(commande.getClient().getId())
                .build();
    }

    public static Commande toEntity(CommandeRequestDTO dto, Client client) {
        if (dto == null) return null;

        return Commande.builder()
                .dateCommande(dto.getDateCommande())
                .statut(dto.getStatut())
                .client(client)
                .build();
    }

    public static Commande toEntity(CommandeDTO dto, Client client) {
        if (dto == null) return null;

        return Commande.builder()
                .id(dto.getId())
                .dateCommande(dto.getDateCommande())
                .statut(dto.getStatut())
                .client(client)
                .build();
    }
}
