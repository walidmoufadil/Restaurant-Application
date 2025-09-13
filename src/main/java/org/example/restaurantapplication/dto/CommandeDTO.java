package org.example.restaurantapplication.dto;

import lombok.*;
import org.example.restaurantapplication.entity.StatutCommande;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeDTO {
    private int id;
    private Date dateCommande;
    private StatutCommande statut;
    private int clientId;
    private String telephone;  // Ajout du numéro de téléphone
    private List<LigneCommandeDTO> lignesCommande;
}
