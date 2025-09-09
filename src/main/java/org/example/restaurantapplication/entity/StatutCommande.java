package org.example.restaurantapplication.entity;

import lombok.*;

@AllArgsConstructor
@ToString
@Getter
public enum StatutCommande {
    EN_COURS("En cours"),
    PREPAREE("Préparée"),
    LIVREE("Livrée");

    private final String libelle;
}
