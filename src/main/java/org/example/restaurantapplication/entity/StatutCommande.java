package org.example.restaurantapplication.entity;

import lombok.*;

@AllArgsConstructor
@ToString
@Getter
public enum StatutCommande {
    EN_ATTENTE("En attente"),
    EN_COURS("En cours"),
    PREPAREE("Préparée"),
    LIVREE("Livrée"),
    ANNULEE("Annulée");

    private final String libelle;
}
