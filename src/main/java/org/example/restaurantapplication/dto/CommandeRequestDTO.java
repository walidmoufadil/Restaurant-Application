package org.example.restaurantapplication.dto;

import lombok.*;
import org.example.restaurantapplication.entity.StatutCommande;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeRequestDTO {
    private Date dateCommande;
    private StatutCommande statut;
    private String telephone;  // Changement de clientId vers telephone
    private List<LigneCommandeRequestDTO> lignesCommande;  // Ajout des lignes de commande
}
