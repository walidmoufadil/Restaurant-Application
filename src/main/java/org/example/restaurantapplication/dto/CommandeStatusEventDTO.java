package org.example.restaurantapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.restaurantapplication.entity.StatutCommande;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeStatusEventDTO {
    private int commandeId;
    private StatutCommande nouveauStatut;
    private String telephone;
}
